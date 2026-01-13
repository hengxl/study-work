package com.hxl.aspect.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 日历映射器接口
 *
 * @author hengxiaoliang
 */
@Mapper
public interface CalendarMapper {

    /**
     * 统计指定日期范围内的记录数量
     */
    int countDateInRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate  endDate);

    /**
     * 批量插入日期
     * @param dateList 日期列表
     */
    void batchInsertDates(@Param("dateList") List<LocalDate> dateList);
}
