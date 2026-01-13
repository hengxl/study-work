package com.hxl.aspect.common.aspect;

import com.google.common.base.Throwables;
import com.hxl.aspect.common.annotation.EnableFillCalendar;
import com.hxl.aspect.common.model.ITableNameHolder;
import com.hxl.aspect.database.IDatabaseService;
import com.hxl.aspect.mapper.CalendarMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * key: 要执行的方法 Method
     * value: 是否标注了 @EnableFillCalendar 注解
     */
    private static final Map<Method, Boolean> METHOD_ANNOTATION_CACHE = new HashMap<>(64);

    private static final String DEDUCTION_TABLE_TEMPLATE = "t_deduction";
    private static final String MAX_ONLINE_NUM_TABLE_TEMPLATE = "t_max_online_num";


    @Around("execution(* com.hxl.aspect.mapper.DeductionMapper.*(..))")
    public Object handleCalendar(ProceedingJoinPoint joinPoint)
            throws Throwable {
        return autoHandleTables(joinPoint, DEDUCTION_TABLE_TEMPLATE);
    }

    /**
     * 自动处理表数据异常
     *
     * @param joinPoint     目标方法
     * @param tableTemplate 表模板
     */
    private Object autoHandleTables(ProceedingJoinPoint joinPoint, String tableTemplate)
            throws Throwable {
        String tableName = null;
        try {
            Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
            // 缓存命中 => 已处理过 targetMethod
            if (METHOD_ANNOTATION_CACHE.containsKey(targetMethod)) {
                // 标注了 @EnableFillCalendar 注解
                if (METHOD_ANNOTATION_CACHE.get(targetMethod)) {
                    tableName = getTableNameFromArgs(joinPoint.getArgs());
                    fillCalendar(tableName);
                    // 执行目标方法
                    return joinPoint.proceed();
                }
                // 未标注 @EnableFillCalendar 注解, 直接执行方法返回最新结果
                return joinPoint.proceed();
            }

            // Map缓存不存在记录 => 未处理过目标方法，先执行一次判断注解
            EnableFillCalendar annotation = AnnotationUtils.findAnnotation(targetMethod, EnableFillCalendar.class);
            if (Objects.nonNull(annotation)) {
                METHOD_ANNOTATION_CACHE.put(targetMethod, true);
                tableName = getTableNameFromArgs(joinPoint.getArgs());
                fillCalendar(tableName);
                return joinPoint.proceed();
            }
            METHOD_ANNOTATION_CACHE.put(targetMethod, false);
            return joinPoint.proceed();
        } catch (Throwable ex) {
            Throwable root = Throwables.getRootCause(ex);
            if (databaseService.needCreateTable(root)) {
                // 从参数中获取表名
                if (Objects.isNull(tableName)) {
                    tableName = getTableNameFromArgs(joinPoint.getArgs());
                }
                // 创建表
                databaseService.createTable(tableName, tableTemplate);
                return joinPoint.proceed();
            } else {
                throw ex;
            }
        }
    }

    /**
     * 判断是否需要填充 calendar 表数据
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
