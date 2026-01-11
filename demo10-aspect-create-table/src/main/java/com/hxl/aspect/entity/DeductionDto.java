package com.hxl.aspect.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeductionDto {

    /**
     * 开始时间（对应数据库 DATETIME 类型，修改为 Date）
     */
    private LocalDate startTime;

    /**
     * 结束时间（对应数据库 DATETIME 类型，修改为 Date）
     */
    private LocalDate endTime;
}