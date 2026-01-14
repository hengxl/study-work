package com.hxl.aspect.service.impl;

import com.hxl.aspect.entity.OnlineNum;
import com.hxl.aspect.entity.StatisticsDto;
import com.hxl.aspect.mapper.OnlineNumMapper;
import com.hxl.aspect.service.OnlineNumService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 在线人数服务实现类
 *
 * @author hengxiaoliang
 */
@Service
public class OnlineNumServiceImpl implements OnlineNumService {

    @Resource
    private OnlineNumMapper onlineNumMapper;

    @Override
    public Integer getOnlineNum(StatisticsDto statisticsDto) {
        OnlineNum onlineNum = new OnlineNum();
        onlineNum.setStartTime(statisticsDto.getStartTime().toString());
        onlineNum.setEndTime(statisticsDto.getEndTime().toString());
        onlineNum.setTableName(getTableName(statisticsDto.getStartTime()));
        return onlineNumMapper.selectOnlineNum(onlineNum);
    }

    /**
     * 获取表名: t_deduction_year_month
     * @param startTime 开始时间
     * @return 表名
     */
    private String getTableName(LocalDate startTime) {
        int year = startTime.getYear();
        int month = startTime.getMonthValue();
        return String.format("t_online_num_%d_%d", year, month);
    }
}
