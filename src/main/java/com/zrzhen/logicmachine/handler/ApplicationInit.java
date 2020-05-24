package com.zrzhen.logicmachine.handler;


import com.zrzhen.logicmachine.factfuntion.AdvanceSore;
import com.zrzhen.logicmachine.factfuntion.R360Sore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 项目初始化配置
 * 实现CommandLineRunner，会在项目启动后执行run方法
 *
 * @author chenanlian
 */
@Component
public class ApplicationInit implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInit.class);


    @Override
    public void run(String... args) throws Exception {
        R360Sore.init();
        AdvanceSore.init();
    }

}
