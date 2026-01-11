package com.hxl.aspect.entity;

import com.hxl.aspect.common.model.ITableNameHolder;
import lombok.Data;

/**
 * 基础实体类
 * 所有子类都需要实现这个接口，用于获取表名
 */
@Data
public class BaseEntity implements ITableNameHolder {
    /**
     * 表名
     */
    private String tableName;
}
