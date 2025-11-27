package com.hxl.domain.model;

import lombok.Getter;

/**
 * 简单相应对象
 * @author hengxiaoliang
 */
@Getter
public class SimpleResponse<T> {
    private Integer code;
    private String msg;

    public SimpleResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public SimpleResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
