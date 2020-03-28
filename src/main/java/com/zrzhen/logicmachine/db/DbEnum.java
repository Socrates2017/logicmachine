package com.zrzhen.logicmachine.db;

/**
 * 数据源枚举
 */
public enum DbEnum {

    MARKET("market", new DbSource("com.mysql.cj.jdbc.Driver",
            DbConstant.dbMarketUrl,
            DbConstant.dbMarketUser,
            DbConstant.dbMarketPassword,
            true,
            DbConstant.dbMarketInitialSize,
            DbConstant.dbMarketMaxActive,
            DbConstant.dbMarketMinIdle,
            DbConstant.dbMarketMaxWait
    )),
    RISK("risk", new DbSource("com.mysql.cj.jdbc.Driver",
            DbConstant.dbRiskUrl,
            DbConstant.dbRiskUser,
            DbConstant.dbRiskPassword,
            true,
            DbConstant.dbRiskInitialSize,
            DbConstant.dbRiskMaxActive,
            DbConstant.dbRiskMinIdle,
            DbConstant.dbRiskMaxWait
    )),
    RULE("rule", new DbSource("com.mysql.cj.jdbc.Driver",
            DbConstant.dbRuleUrl,
            DbConstant.dbRuleUser,
            DbConstant.dbRulePassword,
            true,
            DbConstant.dbRuleInitialSize,
            DbConstant.dbRuleMaxActive,
            DbConstant.dbRuleMinIdle,
            DbConstant.dbRuleMaxWait
    )),
    MARKET_WITHOUT_POOL("market", new DbSource("com.mysql.cj.jdbc.Driver",
            DbConstant.dbMarketUrl,
            DbConstant.dbMarketUser,
            DbConstant.dbMarketPassword
    ));

    private String name;
    private DbSource db;

    public static DbSource getDbByName(String name) {
        DbEnum[] dbEnums = DbEnum.values();
        for (int i = 0; i < dbEnums.length; i++) {
            if (dbEnums[i].getName().equals(name)) {
                return dbEnums[i].getDb();
            }
        }
        return null;
    }


    DbEnum(String name, DbSource db) {
        this.name = name;
        this.db = db;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DbSource getDb() {
        return db;
    }

    public void setDb(DbSource db) {
        this.db = db;
    }
}
