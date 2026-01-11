package com.hxl.aspect.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeductionVo {
    /**
     * 日期（格式：yyyy-MM-dd）
     */
    private String dateStr;

    /**
     * 扣除数据量
     */
    private BigDecimal totalDataSize;
}
