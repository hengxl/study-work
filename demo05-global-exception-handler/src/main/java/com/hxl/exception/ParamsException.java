package com.hxl.exception;

import com.hxl.constant.ErrorEnum;

/**
 * 参数异常
 *
 * @author hengxl
 */
public class ParamsException extends BaseException {

    public ParamsException(Integer code, String message) {
        super(code, message);
    }

    public ParamsException(String message) {
        super(message);
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsException(Throwable cause) {
        super(cause);
    }

    public ParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 条件不为 true 返回异常
     */
    public static void isTrue(boolean expression, ErrorEnum errorEnum) {
        isTrue(expression, errorEnum.getCode(), errorEnum.getDes());
    }

    /**
     * 条件不为 true 返回异常
     */
    public static void isTrue(boolean expression, Integer code, String message) {
        if (!expression) {
            throw new ParamsException(code, message);
        }
    }
}
