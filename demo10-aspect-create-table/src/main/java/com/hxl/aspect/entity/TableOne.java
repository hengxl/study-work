package com.hxl.aspect.entity;

import com.hxl.aspect.common.model.ITableNameHolder;
import lombok.Data;

@Data
public class TableOne implements ITableNameHolder {

    private Long id;

    private String tableName;
}
