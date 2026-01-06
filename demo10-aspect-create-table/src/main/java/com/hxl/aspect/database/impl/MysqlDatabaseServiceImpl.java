package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;

@Service
public class MysqlDatabaseServiceImpl implements IDatabaseService {

    @Autowired
    protected NamedParameterJdbcOperations operations;

    @Override
    public boolean needCreateTable(Throwable ex) {
        if (!(ex instanceof SQLSyntaxErrorException) ){
            return false;
        }
        SQLSyntaxErrorException exception = (SQLSyntaxErrorException) ex;
        return "42S02".equalsIgnoreCase(exception.getSQLState());
    }

    @Override
    public void createTable(String tableName) {
        JdbcOperations jdbcOperations = operations.getJdbcOperations();
        // 创建表
        jdbcOperations.execute(String.format("CREATE TABLE IF NOT EXISTS %s LIKE t_table", tableName));
        // 插入默认数据
        String insertSql = String.format("INSERT INTO %s (id, table_name) VALUES (%d, '%s')",
                tableName, 1L, tableName.replace("'", "''"));
        jdbcOperations.execute(insertSql);
    }
}
