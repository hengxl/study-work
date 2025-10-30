package com.hxl.remote.domain.vo;

import lombok.Data;

import java.util.Map;

@Data
public class DataVO {

    private Integer code;

    private String msg;

    Map<String, String> data;
}
