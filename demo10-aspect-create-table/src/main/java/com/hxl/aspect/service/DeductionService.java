package com.hxl.aspect.service;

import com.hxl.aspect.entity.StatisticsDto;
import com.hxl.aspect.entity.DeductionVo;

import java.util.List;

public interface DeductionService {

    List<DeductionVo> getDeduction(StatisticsDto statisticsDto);
}
