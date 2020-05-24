package com.zrzhen.logicmachine.constant;

import java.util.ArrayList;
import java.util.List;

public enum ConnectiveEnum {

    AND("AND"),
    OR("OR"),
    NOT("NOT");

    private String code;

    ConnectiveEnum(String code) {
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
        ConnectiveEnum[] enums = ConnectiveEnum.values();
        for (ConnectiveEnum connectiveEnum : enums) {
            codeList.add(connectiveEnum.getCode());
        }
        return codeList;
    }
}
