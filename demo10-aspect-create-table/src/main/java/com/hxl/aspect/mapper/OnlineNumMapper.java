package com.hxl.aspect.mapper;

import com.hxl.aspect.entity.OnlineNum;
import org.apache.ibatis.annotations.Mapper;

/**
 * 在线人数映射器
 */
@Mapper
public interface OnlineNumMapper {

    /**
     * 查询在线人数
     * @param onlineNum 查询参数
     * @return 在线人数
     */
    Integer selectOnlineNum(OnlineNum onlineNum);
}
