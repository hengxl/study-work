package com.hxl.exception;

/**
 * 基础异常类 - 所有自定义异常的父类
 */
public class BaseException extends RuntimeException {
    /**
     * 默认无参构造
     */
    public BaseException() {
        super();
    }

    /**⭐⭐⭐⭐⭐
     * 带错误信息构造 - 创建包含自定义错误信息的异常
     * @param message 错误描述信息
     */
    public BaseException(String message) {
        super(message);
    }

    /**⭐⭐⭐⭐
     * 带错误信息和原因构造 - 创建包含错误信息和根本原因的异常
     * @param message 错误描述信息
     * @param cause 根本原因异常
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**⭐⭐⭐
     * 带原因构造 - 包装其他异常作为根本原因
     * @param cause 根本原因异常
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * 完整参数构造 - 包含异常链和堆栈跟踪控制的完整构造
     * @param message 错误描述信息
     * @param cause 根本原因异常
     * @param enableSuppression 是否启用异常抑制
     * @param writableStackTrace 是否生成堆栈跟踪
     */
    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}