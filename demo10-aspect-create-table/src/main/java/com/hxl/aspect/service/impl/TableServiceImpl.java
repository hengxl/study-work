package com.hxl.aspect.service.impl;

import com.hxl.aspect.entity.TableOne;
import com.hxl.aspect.entity.TableTow;
import com.hxl.aspect.mapper.TableMapper;
import com.hxl.aspect.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TableServiceImpl implements TableService {

    @Resource
    private TableMapper tableMapper;

    @Autowired
    protected NamedParameterJdbcOperations operations;

    @Override
    public List<TableOne> getTableOne(String tableName) {
        tableName = validAndGetName(tableName);

        TableOne tableOne = new TableOne();
        tableOne.setTableName(tableName);

        return tableMapper.getTableOneInfo(tableOne);
    }

    @Override
    public List<TableTow> getTableTow(String tableName) {
        tableName = validAndGetName(tableName);

        TableTow tableTow = new TableTow();
        tableTow.setTableName(tableName);

        return tableMapper.getTableTwoInfo(tableTow);
    }

    private static @NonNull String validAndGetName(String tableName) {
        // 其它业余逻辑
        // 校验表名是否合法
        if (!StringUtils.hasText(tableName)) {
            throw new IllegalArgumentException("表名2不能为空");
        }
        log.info("校验表名2完成...");

        //----------- 关键方法 ---------
        tableName = "t_table_" + tableName;
        return tableName;
    }
}
