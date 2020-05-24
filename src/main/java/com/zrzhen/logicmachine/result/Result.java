package com.zrzhen.logicmachine.result;


import com.zrzhen.logicmachine.util.JsonUtil;

/**
 * 统一API响应结果封装
 *
 * @author chenanlian
 */
public class Result<T> implements BaseResult {
    private String code;
    private String msg;
    private T data;


    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.data = data;
    }


    public static Result buildSuccess() {
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result<T> buildSuccess(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }


    public static Result build(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static <T> Result<T> build(ResultCode resultCode, T data) {
        return new Result(resultCode, data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}
