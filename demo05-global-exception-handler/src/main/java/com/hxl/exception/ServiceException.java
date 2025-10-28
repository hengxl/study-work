package com.hxl.exception;

import lombok.Getter;

/**
 * TODO
 *   服务层异常 (ServiceException)
 *   定位：服务执行过程中的技术问题
 *   场景：底层服务调用失败或数据处理异常
 *   示例：
 *       1.数据库操作失败
 *       2.第三方接口调用超时
 *       3.数据格式转换错误
 *   特点：技术层面的、非预期的、可能需要系统干预
 */
@Getter
public class ServiceException extends BaseException {
    /**
     * 必须通过构造器进行初始化
     */
    private final Integer code;

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }


}
