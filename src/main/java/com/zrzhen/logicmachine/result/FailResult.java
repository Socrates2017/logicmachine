package com.zrzhen.logicmachine.result;


import com.zrzhen.logicmachine.util.JsonUtil;

/**
 * 统一API响应结果封装
 *
 * @author chenanlian
 */
public class FailResult implements BaseResult {
    private String code;
    private String msg;

    public FailResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public static FailResult build(ResultCode resultCode) {
        return new FailResult(resultCode);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}
