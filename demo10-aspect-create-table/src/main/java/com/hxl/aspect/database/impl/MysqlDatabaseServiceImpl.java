package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;

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
        if (!(ex instanceof SQLSyntaxErrorException)) {
            return false;
        }
        SQLSyntaxErrorException exception = (SQLSyntaxErrorException) ex;
        return "42S02".equalsIgnoreCase(exception.getSQLState());
    }

    @Override
    public void createTable(String tableName, String tableTemplate) {
        JdbcOperations jdbcOperations = operations.getJdbcOperations();
        jdbcOperations.execute(String.format("CREATE TABLE IF NOT EXISTS %s LIKE %s", tableName, tableTemplate));
        //-------------------------- 插入一些数据 --------------------------
        // 从表名中获取年月
        String[] tableNameSplit = tableName.split("_");
        String year = tableNameSplit[tableNameSplit.length - 2];
        String month = tableNameSplit[tableNameSplit.length - 1];
        // 补全月份格式（如 "1" 转为 "01"），保证日期字符串规范（yyyy - MM）
        String monthFormatted = String.format("%02d", Integer.parseInt(month));
        String datePrefix = year + "-" + monthFormatted;

        // 构建动态插入SQL（替换固定年月为解析后的动态值）
        String insertSql = String.format("""
                        INSERT INTO %s (start_time, end_time, data_size)
                        VALUES
                        -- 当月1号 消费记录（1条，验证单条数据统计）
                        ('%s-01 08:30:00', '%s-01 18:45:00', 100.500000),
                        -- 当月3号 消费记录（1条，大额单条数据）
                        ('%s-03 09:15:00', '%s-03 20:30:00', 200.800000),
                        -- 当月2号 消费记录（2条，验证 sum 求和逻辑，总金额 50.200000+78.900000=129.100000）
                        ('%s-02 10:00:00', '%s-02 16:20:00', 50.200000),
                        ('%s-02 17:00:00', '%s-02 22:50:00', 78.900000),
                        -- 当月5号 消费记录（1条，超大额数据，验证高精度小数统计）
                        ('%s-05 07:40:00', '%s-05 19:10:00', 500.123456),
                        -- 当月8号 消费记录（2条，验证小额数据求和，总金额 10.000000+25.678901=35.678901）
                        ('%s-08 11:20:00', '%s-08 14:30:00', 10.000000),
                        ('%s-08 15:00:00', '%s-08 18:00:00', 25.678901);
                        """, tableName,
                datePrefix, datePrefix,
                datePrefix, datePrefix,
                datePrefix, datePrefix,
                datePrefix, datePrefix,
                datePrefix, datePrefix,
                datePrefix, datePrefix,
                datePrefix, datePrefix
        );

        // 执行插入SQL（使用JdbcOperations执行更新操作）
        jdbcOperations.execute(insertSql);
    }
}
