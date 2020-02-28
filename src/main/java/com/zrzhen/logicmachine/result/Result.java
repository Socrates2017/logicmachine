package com.zrzhen.logicmachine.result;


import com.zrzhen.logicmachine.util.JsonUtil;

/**
 * 统一API响应结果封装
 *
 * @author chenanlian
 */
public class Result<T> implements BaseResult {
    private int status;
    private String message;
    private T data;


    public Result(ResultCode resultCode) {
        this.status = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.status = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }
}
