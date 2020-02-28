package com.zrzhen.logicmachine.handler;


import com.zrzhen.logicmachine.result.ResultCode;

/**
 * 自定义数据服务异常
 *
 * @author chenanlian
 */
public class DataServiceException extends RuntimeException {

    private ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public DataServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
