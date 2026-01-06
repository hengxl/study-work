package com.hxl.aspect.service;

import com.hxl.aspect.entity.TableOne;
import com.hxl.aspect.entity.TableTow;

import java.util.List;

public interface TableService {
    /**
     * 获取表1信息
     */
    List<TableOne> getTableOne(String tableName);

    /**
     * 获取表2信息
     */
    List<TableTow> getTableTow(String tableName);
}
