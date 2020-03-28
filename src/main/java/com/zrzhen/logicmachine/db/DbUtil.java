package com.zrzhen.logicmachine.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {

    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);


    /********************************************手动事务*****************************************/
    /**
     * 开始一个事务,关闭事务自动提交;连接会放进线程，用完后必须要清理threadlocal。在commit方法和rollback方法中都有清理动作；所以不需要额外显示调用
     *
     * @throws SQLException
     */
    public static void begin(DbSource db) throws SQLException {
        Connection conn = getConnectionAndSetThread(db);
        conn.setAutoCommit(false);
    }


    /**
     * 提交一个事务
     *
     * @throws SQLException
     */
    public static void commit(DbSource db) throws SQLException {
        Connection conn = getConnectionInThread(db);
        conn.commit();
        closeConnectionInThread(db);
    }

    /**
     * 回滚一个事务
     */
    public static void rollback(DbSource db) {
        try {
            Connection conn = getConnectionInThread(db);
            conn.rollback();
            closeConnectionInThread(db);
        } catch (SQLException e) {
            log.error("数据库回滚错误:", e);
        }
    }

    /**
     * 关闭线程中的连接
     *
     * @param db
     */
    public static void closeConnectionInThread(DbSource db) {
        db.closeConnectionInThread();
    }


    /**
     * 从thread local中获得连接
     *
     * @param db
     * @return
     */
    public static Connection getConnectionInThread(DbSource db) {
        return db.getConnectionInThread();
    }


    /**
     * 获取一个连接，并放入thread local中；在开启手动事务的时候从此处获取
     *
     * @param db
     * @return
     */
    public static Connection getConnectionAndSetThread(DbSource db) {
        return db.getConnectionAndSetThread();
    }


    /***********************************自动事务***********************************************/
    /**
     * 从连接池中获取连接；如果不开启手动提交事务，则从此处获取
     *
     * @param db
     * @return
     */
    protected static Connection getConnectionFromPool(DbSource db) {
        DruidDataSource dataSource = db.getDataSource();
        /**
         * 如果连接池为空，则创建
         */
        if (dataSource == null) {
            dataSource = getDataSource(db);
            db.setDataSource(dataSource);
        }
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 获取连接池
     *
     * @param db
     * @return
     */
    private static DruidDataSource getDataSource(DbSource db) {
        return getDataSource(db.getDriver(), db.getUrl(), db.getUser(), db.getPassword(),
                db.getInitialSize(), db.getMaxActive(), db.getMinIdle(), db.getMaxWait(), true);
    }

    /**
     * 获取连接池
     *
     * @param driver
     * @param url
     * @param user
     * @param password
     * @param initialSize
     * @param maxActive
     * @param minIdle
     * @param maxWait
     * @param keepAlive
     * @return
     */
    private static DruidDataSource getDataSource(String driver, String url, String user, String password,
                                                 int initialSize, int maxActive, int minIdle, int maxWait,
                                                 boolean keepAlive) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setKeepAlive(keepAlive);
        dataSource.setValidationQuery("SELECT 1");//SQL查询,用来验证从连接池取出的连接,在将连接返回给调用者之前.如果指定, 则查询必须是一个SQL SELECT并且必须返回至少一行记录
        dataSource.setTestOnBorrow(false);//指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.
        dataSource.setTestOnReturn(false);//指明是否在归还到池中前进行检验  注意: 设置为true后如果要生效,validationQuery参数必须设置为非空字符串
        dataSource.setTestWhileIdle(true);//指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败, 则连接将被从池中去除.
        return dataSource;
    }

    /*********************************辅助**************************/


    /**
     * 拼接sql语句，不用于执行，仅用于日志
     *
     * @param sql      sql语句
     * @param bindArgs 绑定的参数
     * @return 拼接后的sql
     */
    public static String sqlStatement(String sql, Object[] bindArgs) {
        StringBuilder sb = new StringBuilder(sql);
        if (bindArgs != null && bindArgs.length > 0) {
            int index = 0;
            for (int i = 0; i < bindArgs.length; i++) {
                index = sb.indexOf("?", index);
                Object arg = bindArgs[i];
                String arg1;
                if (arg instanceof String) {
                    arg1 = "'" + bindArgs[i] + "'";
                } else {
                    arg1 = String.valueOf(bindArgs[i]);
                }
                sb.replace(index, index + 1, arg1);
            }
        }
        return sb.toString();
    }

    /**
     * 将结果集对象封装成List<Map<String, Object>> 对象
     *
     * @param resultSet 结果集
     * @return 结果的封装
     * @throws SQLException
     */
    private static List<Map<String, Object>> getDatas(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>();
        /**获取结果集的数据结构对象**/
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            datas.add(rowMap);
        }
        return datas;
    }


    /*********************************************************写操作************************************/
    /**
     * 可以执行新增，修改，删除
     * 手动提交，操作完成后需要执行commit，或rollback
     *
     * @return 影响的行数
     * @throws SQLException
     * @throws SqlNotFormatException 绑定参数不能为空异常
     */
    public static int update(DbSource db)
            throws SQLException, SqlNotFormatException {
        String sql = db.getSql();
        Object[] bindArgs = db.getBindArgs();

        Connection connection = getConnectionAndSetThread(db);
        connection.setAutoCommit(false);

        /**执行SQL预编译**/
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (bindArgs != null) {
            /**绑定参数设置sql占位符中的值**/
            for (int i = 0; i < bindArgs.length; i++) {
                preparedStatement.setObject(i + 1, bindArgs[i]);
            }
        } else {
            throw new SqlNotFormatException("危险sql,绑定参数为空！SQL:" + sql);
        }
        /**执行sql**/
        /**影响的行数**/
        int affectRowCount = preparedStatement.executeUpdate();
        String operate;
        if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
            operate = "删除";
        } else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
            operate = "新增";
        } else {
            operate = "修改";
        }

        if (log.isDebugEnabled()) {
            log.debug("成功{}了{}行,sql:{}", operate, affectRowCount, sqlStatement(sql, bindArgs));
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return affectRowCount;
    }


    /**
     * 写操作，自动提交
     *
     * @param db
     * @return
     * @throws SQLException
     * @throws SqlNotFormatException
     */
    public static int updateAutocommit(DbSource db)
            throws SQLException, SqlNotFormatException {
        String sql = db.getSql();
        Object[] bindArgs = db.getBindArgs();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int affectRowCount;
        try {

            connection = getConnectionFromPool(db);
            connection.setAutoCommit(true);

            /**执行SQL预编译**/
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                /**绑定参数设置sql占位符中的值**/
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            } else {
                throw new SqlNotFormatException("危险sql,绑定参数为空！SQL:" + sql);
            }
            /**执行sql**/
            /**影响的行数**/
            affectRowCount = preparedStatement.executeUpdate();

            //connection.commit();

            String operate;
            if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
                operate = "删除";
            } else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
                operate = "新增";
            } else {
                operate = "修改";
            }

            if (log.isDebugEnabled()) {
                log.debug("成功{}了{}行,sql:{}", operate, affectRowCount, sqlStatement(sql, bindArgs));
            }
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return affectRowCount;
    }


    /****************************************查询*********************************************************************/

    /**
     * 查询
     *
     * @param db
     * @return
     */
    public static List<Map<String, Object>> query(DbSource db) {
        if (db.isUseConnectPool()) {
            return queryWithPool(db);
        } else {
            return queryWithOutPool(db);
        }
    }

    /**
     * 有连接池的查询
     *
     * @param db
     * @return
     */
    private static List<Map<String, Object>> queryWithPool(DbSource db) {
        Connection conn = getConnectionFromPool(db);
        try {
            if (conn == null) {
                throw new DbException("Get connection fail");
            } else {
                return queryByConnection(conn, db.getSql(), db.getBindArgs());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 无连接池查询
     *
     * @param db
     * @return
     */
    private static List<Map<String, Object>> queryWithOutPool(DbSource db) {
        try {
            Class.forName(db.getDriver());
            Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            return queryByConnection(conn, db.getSql(), db.getBindArgs());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 通过sql查询数据,
     * 慎用，如果传入的参数直接进行拼接，会有sql注入问题
     *
     * @param sql
     * @return 查询的数据集合
     * @throws SQLException
     */
    public static List<Map<String, Object>> query(Connection conn, String sql) throws SQLException {
        return executeQuery(conn, sql, null);
    }


    /**
     * 查询
     *
     * @param conn
     * @param sql
     * @param bindArgs
     * @return
     * @throws SQLException
     */
    private static List<Map<String, Object>> queryByConnection(Connection conn, String sql, Object[] bindArgs) throws SQLException {
        return executeQuery(conn, sql, bindArgs);
    }


    /**
     * @param sql      sql语句
     * @param bindArgs 绑定的参数
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> executeQuery(Connection conn, String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> datas;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (bindArgs != null) {
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            log.debug(sqlStatement(sql, bindArgs));
            resultSet = preparedStatement.executeQuery();
            datas = getDatas(resultSet);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return datas;
    }


}