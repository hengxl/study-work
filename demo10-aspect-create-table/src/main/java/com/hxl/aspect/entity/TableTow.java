package com.hxl.aspect.entity;

import com.hxl.aspect.common.model.ITableNameHolder;
import lombok.Data;

@Data
public class TableTow implements ITableNameHolder {

    private Long id;

    private String tableName;
}
