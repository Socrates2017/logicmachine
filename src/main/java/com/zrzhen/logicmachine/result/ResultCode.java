package com.zrzhen.logicmachine.result;


/**
 * @author chenanlian
 * <p>
 * 返回码规范
 * <p>
 * 返回码分两类，一类与http返回码一致，如404,503。一类为自定义返回码。下面定义自定义返回码。
 * <p>
 * 返回码由三部分组成：错误类型1+错误类型2+错误类型3。
 * <p>
 * 错误类型1区分错误是系统错误，还是业务错误，占一位。1表示成功，2表示系统错误，3表示业务错误，9表示未知或未归类类型。
 * <p>
 * 错误类型2是错误类型1下的再分类，占两位，其中99表示未知或未归类类型。
 * <p>
 * 错误类型3表示最细粒度的错误，占两位，其中99表示未知或未归类类型。
 * <p>
 * 特殊码：99999表示未知的错误。10000表示成功的请求。
 */

public enum ResultCode {

    /**
     * 特殊码
     */
    SUCCESS(1, "ok"),
    FAIL(0, "fail"),


    /**
     * 系统错误
     */
    METHOD_ARGUMENT_NOT_VALID(20000, "参数无效"),
    HTTP_MESSAGE_NOT_READABLE(20001, "HttpMessageNotReadableException"),
    HTTP_RUNTIME_EXCEPTION(20002, "运行时异常"),
    HTTP_ARGUMENT_QRY_DATE_TOO_LARGER(20003, "查询日期qryDate不在查询范围"),
    DATA_NOT_EXISTS(20004, "数据不存在"),
    TYPE_MISMATCH_EXCEPTION(20005, "参数类型不匹配"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(20006, "参数缺失"),

    ;

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
