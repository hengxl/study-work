package com.hxl.domain.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class ProductVO {
    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格（字符串格式，带货币符号）
     */
    private String price;

    /**
     * 库存状态（非具体数量）
     */
    private Boolean stockStatus;

    /**
     * 创建时间（格式化字符串）
     */
    private String createTime;

    /**
     * 更新时间（格式化字符串）
     */
    private String updateTime;

    /**
     * 产品标签（逗号分隔的字符串）
     */
    private String tagString;

    /**
     * 产品面积
     */
    private BigDecimal area;
}
