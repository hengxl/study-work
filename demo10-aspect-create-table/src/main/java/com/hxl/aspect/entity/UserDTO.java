package com.hxl.aspect.entity;

import com.hxl.aspect.common.model.ITableNameHolder;
import lombok.Data;

@Data
public class UserDTO implements ITableNameHolder {

    private Long userId;

    private String tableName;
}
