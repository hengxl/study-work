package com.hxl.aspect.database.impl;


import com.hxl.aspect.database.IDatabaseService;
import com.kingbase8.util.KSQLException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.kingbase8.Driver")
public class KingbaseDatabaseServiceImpl implements IDatabaseService {

    @Override
    public boolean needCreateTable(Throwable ex) {
        if (!(ex instanceof KSQLException)) {
            return false;
        }
        KSQLException exception = (KSQLException) ex;
        return "42P01".equalsIgnoreCase(exception.getSQLState());
    }

    @Override
    public void createTable(String templateTable, String tableName) {
        // 暂不实现
    }

    @Override
    public void fillCalendar(String year) {

    }
}
