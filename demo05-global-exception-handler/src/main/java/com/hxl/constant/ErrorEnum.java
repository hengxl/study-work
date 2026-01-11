package com.hxl.constant;

import lombok.Getter;

/**
 * 错误编码枚举
 *
 * @author hengxl
 */
@Getter
public enum ErrorEnum {

    PARAM_NULL(20001, "参数为空"),

    PARAM_INVALID(20002, "参数无效"),

    PARAM_LENGTH_ERROR(20003, "参数长度错误"),

    UNKNOWN_ERROR(20003, "未知错误");

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String des;

    ErrorEnum(int code, String des) {
        this.code = code;
        this.des = des;
    }
}
