package com.hxl.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class ProductDTO {
    /**
     * 产品唯一标识符
     */
    private Long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stockQuantity;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifiedDate;

    /**
     * 产品标签列表
     */
    private List<String> tags;


    /**
     * 产品高度
     */
    private Double high;

    /**
     * 产品宽度
     */
    private Double width;

}
