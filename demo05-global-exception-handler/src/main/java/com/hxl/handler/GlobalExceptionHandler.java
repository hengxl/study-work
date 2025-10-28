package com.hxl.handler;

import com.hxl.bean.util.Result;
import com.hxl.bean.util.ResultUtil;
import com.hxl.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class},
basePackages = {"com.hxl.controller"})
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleIllegalArgumentException(BusinessException ex) {
        String code = ex.getCode().toString();
        String msg = ex.getMessage();
        log.warn("error: code is " + code + " msg is " + msg);
        return ResultUtil.buildFailure(code, msg, null);
    }
}
