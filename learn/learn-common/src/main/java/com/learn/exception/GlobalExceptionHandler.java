package com.learn.exception;


import com.learn.webapi.ResultObject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *    on 2020/2/27.
 */
@ControllerAdvice(basePackages="com.learn.controller")
public class GlobalExceptionHandler {

    static {
        System.out.println(1);
    }
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResultObject handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ResultObject.failed(e.getErrorCode());
        }
        return ResultObject.failed(e.getMessage());
    }
}
