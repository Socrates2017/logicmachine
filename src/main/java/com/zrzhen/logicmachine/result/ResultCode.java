package com.zrzhen.logicmachine.result;

public enum ResultCode {

    /**
     * 特殊码
     */
    SUCCESS("1", "success"),
    FAIL("0", "fail"),


    /**
     * 系统错误
     */
    NO_PERMISSION("20001", "权限不足"),
    METHOD_ARGUMENT_NOT_VALID("20000", "参数无效"),
    HTTP_MESSAGE_NOT_READABLE("20001", "HttpMessageNotReadableException"),
    HTTP_RUNTIME_EXCEPTION("20002", "运行时异常"),
    HTTP_ARGUMENT_QRY_DATE_TOO_LARGER("20003", "查询日期qryDate不在查询范围"),
    DATA_NOT_EXISTS("20004", "数据不存在"),
    TYPE_MISMATCH_EXCEPTION("20005", "参数类型不匹配"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("20006", "参数缺失"),

    DOWNLOAD_FAIL("20006", "文件下载失败"),



    ATOMIC_FACT_EXIST("20006", "原子事实已存在"),
    FACT_EXIST("20006", "事实已存在"),
    ;

    private String code;

    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
