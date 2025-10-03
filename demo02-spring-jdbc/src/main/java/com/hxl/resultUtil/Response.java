package com.hxl.resultUtil;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author hunter
 */
public interface Response extends Serializable {
    /**
     * 返回码
     */
    @JsonPropertyDescription("返回码, 0成功")
    int getResCode();

    /**
     * 返回信息
     */
    @JsonPropertyDescription("返回信息")
    String getResMessage();
}
