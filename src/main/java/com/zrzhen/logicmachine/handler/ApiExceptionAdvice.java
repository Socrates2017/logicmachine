package com.zrzhen.logicmachine.handler;


import com.zrzhen.logicmachine.result.FailResult;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器增强，是controller的增强，和ExceptionHandler一起用来做全局异常
 * 可以配置全局异常处理
 *
 * @author chenanlian
 */
@ControllerAdvice
@ResponseBody
public class ApiExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionAdvice.class);


    /**
     * 数据服务异常
     */
    @ExceptionHandler(DataServiceException.class)
    public FailResult DataServiceException(DataServiceException e) {
        log.error("DataServiceException:", e);
        return ResultGen.genFailResult(e.getResultCode());
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public FailResult runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException:", e);
        return ResultGen.genFailResult(ResultCode.HTTP_RUNTIME_EXCEPTION);
    }

    /**
     * 无法解析JSON异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public FailResult jsonParseException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException:", e);
        return ResultGen.genFailResult(ResultCode.HTTP_MESSAGE_NOT_READABLE);
    }

    /**
     * 参数类型不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler(TypeMismatchException.class)
    public FailResult typeMismatchException(TypeMismatchException e) {
        log.error("MethodArgumentNotValidException:", e);
        return ResultGen.genFailResult(ResultCode.TYPE_MISMATCH_EXCEPTION);
    }

    /**
     * 参数无效异常，如json请求违法约束
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:", e);
        return ResultGen.genFailResult(ResultCode.METHOD_ARGUMENT_NOT_VALID);
    }

    /**
     * 参数缺失异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public FailResult missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MethodArgumentNotValidException:", e);
        return ResultGen.genFailResult(ResultCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
    }


    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public FailResult exceptionHandler(Exception e) {
        log.error("Unknown exception happened:", e);
        return ResultGen.genFailResult(ResultCode.FAIL);
    }

}