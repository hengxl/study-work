package com.hxl.domain.model;

import lombok.Getter;

/**
 * 完整响应对象
 * @author hengxiaoliang
 */
@Getter
public class Response<T> extends SimpleResponse<T> {
    private T result;

    public Response<T> setResult(T result) {
        this.result = result;
        return this;
    }
}
