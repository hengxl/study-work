package com.hxl.aspect.mapper;

import com.hxl.aspect.entity.TableOne;
import com.hxl.aspect.entity.TableTow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper {
    /**
     * 获取表1信息
     */
    // @TableCreation
    List<TableOne> getTableOneInfo(TableOne tableOne);

    /**
     * 获取表2信息
     */
    List<TableTow> getTableTwoInfo(TableTow tableTow);
}
