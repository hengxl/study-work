package com.hxl.aspect.common.aspect;

import com.hxl.aspect.common.model.ITableNameHolder;
import com.hxl.aspect.database.IDatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Aspect
@Component
@Slf4j
public class AutoHandleTableAspect {

    @Autowired
    private IDatabaseService databaseService;

    private static final String DEDUCTION_TABLE_TEMPLATE = "deduction";

    @Around("@annotation(com.hxl.aspect.common.annotation.CheckCalendar)")
    public Object checkCalendar(ProceedingJoinPoint joinPoint)
            throws Throwable {
        log.info("执行目标方法");
        Object proceed = joinPoint.proceed();
        log.info("目标方法执行完成");
        // 转换结果
        List results = (List) proceed;
        if (CollectionUtils.isEmpty(results)) {
            // 日历不完整 => 补充当前日历信息
            String tableName = getTableNameFromArgs(joinPoint.getArgs());
            // 从表明中获取年、月 例如 => t_deduction_2026_1
            if (ObjectUtils.isEmpty(tableName)) {
                // 表名格式错误
                log.error("表名不能为空!");
                throw new RuntimeException("表名不能为空!");
            }
            // 查找最后一个下划线（定位「月」的前置分隔符）
            int lastUnderlineIdx = tableName.lastIndexOf('_');
            // 3. 查找倒数第二个下划线（定位「年」的前置分隔符）
            int secondLastUnderlineIdx = tableName.lastIndexOf('_', lastUnderlineIdx - 1);
            if (lastUnderlineIdx == -1 || secondLastUnderlineIdx == -1 || (lastUnderlineIdx - secondLastUnderlineIdx) <= 1) {
                throw new IllegalArgumentException("表名格式错误，无法提取年份：" + tableName);
            }

            // 4. 截取年份
            String year = tableName.substring(secondLastUnderlineIdx + 1, lastUnderlineIdx);
            // 补充当年日历信息
            databaseService.fillCalendar(year);
        }
        return joinPoint.proceed();
    }


    /**
     * 统一从方法参数中获取表名
     */
    private String getTableNameFromArgs(Object[] args) {
        if (ObjectUtils.isEmpty(args)) {
            return null;
        }
        // 取第一个参数
        Object firstArg = args[0];
        if (firstArg instanceof ITableNameHolder) {
            // 如果参数实现了ITableNameHolder接口，直接调用getTableName方法
            return ((ITableNameHolder) firstArg).getTableName();
        }
        return null;
    }
}
