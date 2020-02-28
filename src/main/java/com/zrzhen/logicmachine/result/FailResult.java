package com.zrzhen.logicmachine.result;


import com.zrzhen.logicmachine.util.JsonUtil;

/**
 * 统一API响应结果封装
 *
 * @author chenanlian
 */
public class FailResult implements BaseResult {
    private int status;
    private String message;

    public FailResult(ResultCode resultCode) {
        this.status = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }
}
