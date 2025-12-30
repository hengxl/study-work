package com.hxl.handler;

import com.google.common.base.Throwables;
import com.hxl.constant.ErrorEnum;
import com.hxl.domain.util.Result;
import com.hxl.domain.util.ResultUtil;
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
 * 用于捕获和统一处理项目中抛出的异常。
 */
@ControllerAdvice(
        // 指定拦截范围：对所有 @Component 和 @Service 注解的类生效
        // 注意：通常应拦截 @RestController 或 @Controller 来处理 Web 请求异常
        annotations = {RestController.class, Controller.class},
        // 指定扫描的基础包
        basePackages = {"com.hxl"}
)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     * @param ex 业务异常
     * @return 统一的错误响应体
     */
    @ExceptionHandler(BusinessException.class) // 声明此方法处理 BusinessException 类型的异常
    @ResponseStatus(HttpStatus.OK) // 设置 HTTP 状态码为 200
    @ResponseBody // 将返回的 Result 对象序列化为 JSON 格式
    public Result handleBusinessException(BusinessException ex) {
        String code = ex.getCode().toString();
        String msg = ex.getMessage();
        log.warn("业务异常: code={}, msg={}", code, msg);
        return ResultUtil.buildFailure(code, msg, null);
    }

    /**
     * Exception
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception ex) {
        log.error("error", Throwables.getRootCause(ex));
        return ResultUtil.buildFailure(ErrorEnum.UNKNOWN_ERROR.getCode().toString(), ErrorEnum.UNKNOWN_ERROR.getDes(), null);
    }
}