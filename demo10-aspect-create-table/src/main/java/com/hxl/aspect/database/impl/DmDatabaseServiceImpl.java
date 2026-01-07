package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import dm.jdbc.driver.DMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

@Service
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
    public void createTable(String templateTable, String tableName) {
        JdbcOperations jdbcOperations = operations.getJdbcOperations();
        // 创建表
        jdbcOperations.execute(String.format("CREATE TABLE IF NOT EXISTS %s LIKE %s", tableName, templateTable));
        // 插入默认数据
        String insertSql = null;
        if (templateTable.contains("user")) {
            insertSql = String.format("INSERT INTO %s (user_id, username, table_name) VALUES (%d, '%s', '%s')",
                    tableName, 100L, "username", tableName);
        } else {
            insertSql = String.format("INSERT INTO %s (room_id, room_name, table_name) VALUES (%d, '%s', '%s')",
                    tableName, 100L, "roomName", tableName);
        }
        jdbcOperations.execute(insertSql);
    }
}
