package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import dm.jdbc.driver.DMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@Profile("dm")
// 根据配置文件中 spring.datasource.driver-class-name 属性的值是否是 dm.jdbc.driver.DmDriver 来判断是否创建该Bean
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "dm.jdbc.driver.DmDriver")
public class DmDatabaseServiceImpl implements IDatabaseService {

    @Autowired
    protected NamedParameterJdbcOperations operations;

    @Override
    public boolean needCreateTable(Throwable ex) {
        if (!(ex instanceof DMException)) {
            return false;
        }
        DMException exception = (DMException) ex;
        return exception.getErrorCode() == -2106;
    }

    @Override
    public void createTable(String tableName, String tableTemplate) {
        JdbcOperations jdbcOperations = operations.getJdbcOperations();
        jdbcOperations.execute("ALTER SESSION SET 'CTAB_SEL_WITH_CONS' = 2");
        jdbcOperations.execute(String.format("CREATE TABLE IF NOT EXISTS %s LIKE %s", tableName, tableTemplate));
    }
}
