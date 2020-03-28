package com.zrzhen.logicmachine.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenanlian
 * 获取配置文件中的配置
 */
@Component
public class DbConstant {

    public static String dbMarketUrl;
    public static String dbMarketUser;
    public static String dbMarketPassword;

    public static Integer dbMarketInitialSize;
    public static Integer dbMarketMaxActive;
    public static Integer dbMarketMinIdle;
    public static Integer dbMarketMaxWait;

    public static String dbRiskUrl;
    public static String dbRiskUser;
    public static String dbRiskPassword;

    public static Integer dbRiskInitialSize;
    public static Integer dbRiskMaxActive;
    public static Integer dbRiskMinIdle;
    public static Integer dbRiskMaxWait;

    public static String dbRuleUrl;
    public static String dbRuleUser;
    public static String dbRulePassword;

    public static Integer dbRuleInitialSize;
    public static Integer dbRuleMaxActive;
    public static Integer dbRuleMinIdle;
    public static Integer dbRuleMaxWait;

    @Value("${DbSource.market.url}")
    public void setDbMarketUrl(String dbMarketUrl) {
        DbConstant.dbMarketUrl = dbMarketUrl;
    }

    @Value("${DbSource.market.user}")
    public void setDbMarketUser(String dbMarketUser) {
        DbConstant.dbMarketUser = dbMarketUser;
    }

    @Value("${DbSource.market.password}")
    public void setDbMarketPassword(String dbMarketPassword) {
        DbConstant.dbMarketPassword = dbMarketPassword;
    }

    @Value("${DbSource.market.initialSize}")
    public void setDbMarketInitialSize(Integer dbMarketInitialSize) {
        DbConstant.dbMarketInitialSize = dbMarketInitialSize;
    }

    @Value("${DbSource.market.maxActive}")
    public void setDbMarketMaxActive(Integer dbMarketMaxActive) {
        DbConstant.dbMarketMaxActive = dbMarketMaxActive;
    }

    @Value("${DbSource.market.minIdle}")
    public void setDbMarketMinIdle(Integer dbMarketMinIdle) {
        DbConstant.dbMarketMinIdle = dbMarketMinIdle;
    }

    @Value("${DbSource.market.maxWait}")
    public void setDbMarketMaxWait(Integer dbMarketMaxWait) {
        DbConstant.dbMarketMaxWait = dbMarketMaxWait;
    }


    @Value("${DbSource.risk.url}")
    public void setDbRiskUrl(String dbRiskUrl) {
        DbConstant.dbRiskUrl = dbRiskUrl;
    }

    @Value("${DbSource.risk.user}")
    public void setDbRiskUser(String dbRiskUser) {
        DbConstant.dbRiskUser = dbRiskUser;
    }

    @Value("${DbSource.risk.password}")
    public void setDbRiskPassword(String dbRiskPassword) {
        DbConstant.dbRiskPassword = dbRiskPassword;
    }

    @Value("${DbSource.risk.initialSize}")
    public void setDbRiskInitialSize(Integer dbRiskInitialSize) {
        DbConstant.dbRiskInitialSize = dbRiskInitialSize;
    }

    @Value("${DbSource.risk.maxActive}")
    public void setDbRiskMaxActive(Integer dbRiskMaxActive) {
        DbConstant.dbRiskMaxActive = dbRiskMaxActive;
    }

    @Value("${DbSource.risk.minIdle}")
    public void setDbRiskMinIdle(Integer dbRiskMinIdle) {
        DbConstant.dbRiskMinIdle = dbRiskMinIdle;
    }

    @Value("${DbSource.risk.maxWait}")
    public void setDbRiskMaxWait(Integer dbRiskMaxWait) {
        DbConstant.dbRiskMaxWait = dbRiskMaxWait;
    }

    @Value("${DbSource.rule.user}")
    public void setDbRuleUser(String dbRuleUser) {
        DbConstant.dbRuleUser = dbRuleUser;
    }

    @Value("${DbSource.rule.password}")
    public void setDbRulePassword(String dbRulePassword) {
        DbConstant.dbRulePassword = dbRulePassword;
    }

    @Value("${DbSource.rule.initialSize}")
    public void setDbRuleInitialSize(Integer dbRuleInitialSize) {
        DbConstant.dbRuleInitialSize = dbRuleInitialSize;
    }

    @Value("${DbSource.rule.maxActive}")
    public void setDbRuleMaxActive(Integer dbRuleMaxActive) {
        DbConstant.dbRuleMaxActive = dbRuleMaxActive;
    }

    @Value("${DbSource.rule.minIdle}")
    public void setDbRuleMinIdle(Integer dbRuleMinIdle) {
        DbConstant.dbRuleMinIdle = dbRuleMinIdle;
    }

    @Value("${DbSource.rule.maxWait}")
    public void setDbRuleMaxWait(Integer dbRuleMaxWait) {
        DbConstant.dbRuleMaxWait = dbRuleMaxWait;
    }
}
