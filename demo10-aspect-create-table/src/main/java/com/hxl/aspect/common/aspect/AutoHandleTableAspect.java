package com.hxl.aspect.common.aspect;

import com.google.common.base.Throwables;
import com.hxl.aspect.common.model.ITableNameHolder;
import com.hxl.aspect.database.IDatabaseService;
import com.hxl.aspect.mapper.CalendarMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自动处理表数据切面
 *
 * @author hxl
 */
@Aspect
@Component
@Slf4j
public class AutoHandleTableAspect {

    @Autowired
    private IDatabaseService databaseService;

    @Autowired
    private CalendarMapper calendarMapper;

    @Around("execution(* com.hxl.aspect.mapper.DeductionMapper.*(..))")
    public Object handleCalendar(ProceedingJoinPoint joinPoint)
            throws Throwable {
        return handleMultiTables(joinPoint, true);
    }

    @Around("execution(* com.hxl.aspect.mapper.OnlineNumMapper.*(..))")
    public Object handleOnlineNum(ProceedingJoinPoint joinPoint)
            throws Throwable {
        return handleMultiTables(joinPoint, false);
    }

    /**
     * 自动创建表 + 填充日历表
     */
    private Object handleMultiTables(ProceedingJoinPoint joinPoint, boolean needFillCalendar)
            throws Throwable {
        String tableName = null;
        try {
            // 如果需要填充日历表，先获取表名并填充
            if (needFillCalendar) {
                tableName = getTableNameFromArgs(joinPoint.getArgs());
                fillCalendar(tableName);
            }
            // 执行目标方法
            return joinPoint.proceed();
        } catch (Throwable ex) {
            Throwable root = Throwables.getRootCause(ex);
            if (databaseService.needCreateTable(root)) {
                // 从参数中获取表名
                if (Objects.isNull(tableName)) {
                    tableName = getTableNameFromArgs(joinPoint.getArgs());
                }
                // 从表名中获取模板表的名字
                String tableTemplate = getTableTemplate(tableName);
                // 创建表
                databaseService.createTable(tableName, tableTemplate);
                return joinPoint.proceed();
            } else {
                throw ex;
            }
        }
    }

    /**
     * 填充 calendar 表数据
     */
    private void fillCalendar(String tableName) {
        String[] tableNameSplit = tableName.split("_");
        // 获取年
        int year = Integer.parseInt(tableNameSplit[tableNameSplit.length - 2]);
        // 获取月
        int month = Integer.parseInt(tableNameSplit[tableNameSplit.length - 1]);
        // 检查 calendar 表是否需要填充数据
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        int count = calendarMapper.countDateInRange(startDate, endDate);
        if (count == 0) {
            // 生成该月所有日期的列表
            List<LocalDate> datesOfMonth = new ArrayList<>(32);
            LocalDate current = startDate;
            while (!current.isAfter(endDate)) {
                datesOfMonth.add(current);
                current = current.plusDays(1);
            }
            calendarMapper.batchInsertDates(datesOfMonth);
        }
    }

    /**
     * 从表名中获取模板表的名字, 格式: tableName <=> tableTemplate_year_month
     */
    private static @NonNull String getTableTemplate(String tableName) {
        int firstLastIndex = tableName.lastIndexOf("_");
        int secondLastUnderlineIndex = tableName.lastIndexOf("_", firstLastIndex - 1);
        return tableName.substring(0, secondLastUnderlineIndex);
    }

    /**
     * 规约: 方法的某一个参数必须实现 ITableNameHolder 接口, 才能获取表名
     */
    private String getTableNameFromArgs(Object[] args) {
        if (ObjectUtils.isEmpty(args)) {
            throw new IllegalArgumentException("args cannot be null");
        }
        for (Object arg : args) {
            if (arg instanceof ITableNameHolder) {
                String tableName = ((ITableNameHolder) arg).getTableName();
                if (StringUtils.hasText(tableName)) {
                    return tableName;
                }
                break;
            }
        }
        throw new IllegalArgumentException("fail to get valid table name from args");
    }
}
