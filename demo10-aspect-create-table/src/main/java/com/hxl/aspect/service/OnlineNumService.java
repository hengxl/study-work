package com.hxl.aspect.service;

import com.hxl.aspect.entity.StatisticsDto;

/**
 * 在线人数服务接口
 *
 * @author hengxiaoliang
 */
public interface OnlineNumService {
    /**
     * 查询在线人数
     * @param statisticsDto 查询参数
     * @return 在线人数
     */
    Integer getOnlineNum(StatisticsDto statisticsDto);
}
