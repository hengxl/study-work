package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@Profile("mysql")
// 根据配置文件中 spring.datasource.driver-class-name 属性的值是否是 com.mysql.cj.jdbc.Driver 来判断是否创建该Bean
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.mysql.cj.jdbc.Driver")
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

    @Override
    public void fillCalendar(String year) {
        int yearInt = Integer.parseInt(year);
        LocalDate start = LocalDate.of(yearInt, 1, 1);
        LocalDate end = LocalDate.of(yearInt, 12, 31);
        int totalDays = (int) ChronoUnit.DAYS.between(start, end) + 1;

        // 构建单条 INSERT 语句 !!!
        StringBuilder sql = new StringBuilder("INSERT INTO t_calendar VALUES ");
        List<Date> params = new ArrayList<>(totalDays);

        for (int i = 0; i < totalDays; i++) {
            if (i > 0) {
                sql.append(",");
            }
            sql.append("(?)");
            params.add(java.sql.Date.valueOf(start.plusDays(i)));
        }

        long startTime = System.currentTimeMillis();
        JdbcOperations jdbc = operations.getJdbcOperations();
        jdbc.update(sql.toString(), params.toArray());
        long costTime = System.currentTimeMillis() - startTime;

        log.info("单条SQL插入 {} 年日历完成，耗时：{}ms", year, costTime);
    }
}
