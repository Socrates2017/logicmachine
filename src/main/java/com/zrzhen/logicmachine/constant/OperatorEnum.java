package com.zrzhen.logicmachine.constant;

import java.util.ArrayList;
import java.util.List;

public enum OperatorEnum {

    EQUALS("="),
    LESS("<"),
    LESS_EQUALS("<="),
    LARGER(">"),
    LARGER_EQUALS(">="),
    ;

    private String code;


    OperatorEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<String> codeList() {
        List<String> codeList = new ArrayList<>();
        OperatorEnum[] enums = OperatorEnum.values();
        for (OperatorEnum operatorEnum : enums) {
            codeList.add(operatorEnum.getCode());
        }
        return codeList;
    }


}
