package com.hxl.aspect;

import com.hxl.aspect.mapper.CalendarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CalendarMapperTest {

    @Autowired
    private CalendarMapper calendarMapper;

    private static final Map<Object, Object> methodMap = new HashMap<>();

    @Test
    public void test() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 31);
        Integer count1 = calendarMapper.countDateInRange(startDate, endDate);

        LocalDate startDate2 = LocalDate.of(2026, 1, 1);
        LocalDate endDate2 = LocalDate.of(2026, 1, 30);
        Integer count2 = calendarMapper.countDateInRange(startDate2, endDate2);
        System.out.println(count1);
        System.out.println(count2);
    }

    @Test
    public void testBatchInsertDates() {
        String tableName = "t_deduction_2023_3";
        String[] tableNameSplit = tableName.split("_");
        // 获取年
        int year = Integer.parseInt(tableNameSplit[tableNameSplit.length - 2]);
        // 获取月
        int month = Integer.parseInt(tableNameSplit[tableNameSplit.length - 1]);
        // 检查 calendar 表是否需要填充数据
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        int count = calendarMapper.countDateInRange(startDate, endDate);
        if (count == 0) {
            // 生成该月所有日期的列表
            List<LocalDate> datesOfMonth = new ArrayList<>(32);
            LocalDate current = startDate;
            while (!current.isAfter(endDate)) {
                datesOfMonth.add(current);
                current = current.plusDays(1);
            }
            long startTime = System.currentTimeMillis();
            calendarMapper.batchInsertDates(datesOfMonth);
            long endTime = System.currentTimeMillis();
            System.out.println("插入 " + datesOfMonth.size() + " 条数据耗时: " + (endTime - startTime) + " ms");
        }
    }
}
