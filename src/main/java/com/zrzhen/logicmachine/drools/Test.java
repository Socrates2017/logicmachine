package com.zrzhen.logicmachine.drools;

import com.zrzhen.logicmachine.rule.Rules;

public class Test {

    public static void main(String[] args) {

        Integer advance = null;
        Integer r360Score = 700;
        Integer result = 0;

        if (advance == null) {
            result = 0;//未决策，无结果
        } else if (advance < 551) {
            result = 1;//拒绝
        } else if (advance >= 650) {
            result = 2;//通过
        } else if (advance < 650 && advance >= 551) {
            result = 3;//人审
        }

        if (result == 0) {
            if (r360Score < 616) {
                result = 1;
            } else if (r360Score > 662) {
                result = 2;
            } else if (r360Score >= 616 && r360Score < 662) {
                result = 3;
            }
        }

        System.out.println(result);



        int rulesId=0;//获取决策id
        Rules rules = new Rules(rulesId);//获取规则集
        rules.excute();



    }
}
