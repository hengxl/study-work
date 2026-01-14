package com.hxl.aspect;

import com.hxl.aspect.entity.Deduction;
import com.hxl.aspect.entity.OnlineNum;
import com.hxl.aspect.mapper.CalendarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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

    /**
     *  测试 @EqualsAndHashCode(callSuper = true) 的效果
     *  实体类继承了 BaseEntity，所以在比较相等性时，会考虑父类的属性
     */
    @Test
    public void testLombokEqualsAndHashCode() {
        // TODO: 没有继承 BaseEntity 的实体类 => 不会考虑继承的父类的属性
        Deduction deduction1 = new Deduction();
        deduction1.setId(1);
        deduction1.setDataSize(BigDecimal.valueOf(100));
        deduction1.setTableName("t_deduction_1"); // 继承的父类属性
        deduction1.setStartTime(LocalDate.of(2023, 1, 1));
        deduction1.setEndTime(LocalDate.of(2023, 1, 31));
        Deduction deduction2 = new Deduction();
        deduction2.setId(1);
        deduction2.setDataSize(BigDecimal.valueOf(100));
        deduction2.setTableName("t_deduction_2"); // 继承的父类属性
        deduction2.setStartTime(LocalDate.of(2023, 1, 1));
        deduction2.setEndTime(LocalDate.of(2023, 1, 31));
        System.out.println(deduction1.equals(deduction2));
        System.out.println("==================================");
        // TODO: 继承了 BaseEntity 的实体类 => 会考虑继承的父类的属性
        OnlineNum onlineNum1 = new OnlineNum();
        onlineNum1.setId(1);
        onlineNum1.setRoomId(2);
        onlineNum1.setTotalOnlineNum(1000);
        onlineNum1.setTableName("t_max_online_num_1"); // 继承的父类属性
        onlineNum1.setStartTime("2023-01-01");
        onlineNum1.setEndTime("2023-01-31");
        OnlineNum onlineNum2 = new OnlineNum();
        onlineNum2.setId(1);
        onlineNum2.setRoomId(2);
        onlineNum2.setTotalOnlineNum(1000);
        onlineNum2.setTableName("t_max_online_num_2"); // 继承的父类属性
        onlineNum2.setStartTime("2023-01-01");
        onlineNum2.setEndTime("2023-01-31");
        System.out.println(onlineNum1.equals(onlineNum2));
    }
}
