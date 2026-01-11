package com.hxl.aspect.entity;

import com.hxl.aspect.common.model.ITableNameHolder;
import lombok.Data;

@Data
public class Room implements ITableNameHolder {

    private Long roomId;

    private String roomName;

    private String tableName;
}
