package com.hxl.aspect.common.aspect;

import com.google.common.base.Throwables;
import com.hxl.aspect.common.model.ITableNameHolder;
import com.hxl.aspect.database.IDatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class TableCreationAspect {

    @Autowired
    private IDatabaseService databaseService;

    /**
     * 基于 execution 表达式判断是否需要创建表
     */
    @Around("execution(* com.hxl.aspect.mapper.TableMapper.*(..))")
    public Object createTableByExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String tableName = null;
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            Throwable root = Throwables.getRootCause(ex);
            // 不是需要创建表的异常，直接抛出
            if (databaseService.needCreateTable(root)) {
                // 获取表名
                tableName = getTableNameFromArgs(joinPoint.getArgs());
                if (Objects.isNull(tableName)) {
                    log.error("无法获取表名，参数类型不匹配!");
                    throw ex;
                }
                // 创建表
                log.info("表不存在! 准备创建表: {}", tableName);
                databaseService.createTable(tableName);
                log.info("重试执行...");
                // 重试执行
                return joinPoint.proceed();
            }
            // 其他异常，直接抛出
            throw ex;
        }
    }

    /**
     * 基于自定义注解（性能会低一点）
     */
    @Around("@annotation(com.hxl.aspect.common.annotation.TableCreation)")
    public Object createTableByAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        String tableName = null;
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            Throwable root = Throwables.getRootCause(ex);
            // 不是需要创建表的异常，直接抛出
            if (databaseService.needCreateTable(root)) {
                // 获取表名
                tableName = getTableNameFromArgs(joinPoint.getArgs());
                if (Objects.isNull(tableName)) {
                    log.error("无法获取表名，参数类型不匹配");
                    throw ex;
                }
                // 创建表
                log.info("表不存在，准备创建表: {}", tableName);
                databaseService.createTable(tableName);
                log.info("表创建成功: {}", tableName);
                // 重试执行
                return joinPoint.proceed();
            }
            // 其他异常，直接抛出
            throw ex;
        }
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