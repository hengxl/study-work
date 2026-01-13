package com.hxl.aspect.database.impl;

import com.hxl.aspect.database.IDatabaseService;
import com.yashandb.jdbc.exception.YasException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.driver-class-name", havingValue = "com.yashandb.jdbc.Driver")
public class YashanDatabaseServiceImpl implements IDatabaseService {

    @Override
    public boolean needCreateTable(Throwable ex) {
        if (!(ex instanceof YasException)) {
            return false;
        }
        YasException exception = (YasException) ex;
        return "2012".equalsIgnoreCase(exception.getSQLState());
    }

    @Override
    public void createTable(String tableName, String tableTemplate) {
        // 暂不实现
    }
}
