package com.zrzhen.logicmachine.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUpate {

    private static final Logger log = LoggerFactory.getLogger(DbUpate.class);


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

        Connection connection = DbConnect.getConnectionAndSetThread(db);
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
            log.debug("成功{}了{}行,sql:{}", operate, affectRowCount, DbConvert.sqlStatement(sql, bindArgs));
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

            connection = DbConnect.getConnectionFromPool(db);
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
                log.debug("成功{}了{}行,sql:{}", operate, affectRowCount, DbConvert.sqlStatement(sql, bindArgs));
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
}
