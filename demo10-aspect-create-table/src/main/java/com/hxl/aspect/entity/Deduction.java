package com.hxl.aspect.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 扣除记录（t_deduction）实体类
 * 对应数据库表 t_deduction
 */
// TODO: true => 在equals方法比较时，会考虑继承的父类的属性
@EqualsAndHashCode(callSuper = true)
@Data
public class Deduction extends BaseEntity {
    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 开始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 消费数据量
     */
    private BigDecimal dataSize;
}