package com.hxl.aspect.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 统计查询参数
 *
 * @author hengxiaoliang
 */
@Data
public class StatisticsDto {

    /**
     * 开始时间（对应数据库 DATETIME 类型，修改为 Date）
     */
    private LocalDate startTime;

    /**
     * 结束时间（对应数据库 DATETIME 类型，修改为 Date）
     */
    private LocalDate endTime;
}