package com.hxl.aspect.service.impl;

import com.hxl.aspect.entity.Deduction;
import com.hxl.aspect.entity.DeductionDto;
import com.hxl.aspect.entity.DeductionVo;
import com.hxl.aspect.mapper.DeductionMapper;
import com.hxl.aspect.service.DeductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DeductionServiceImpl implements DeductionService {

    @Resource
    private DeductionMapper mapper;

    @Override
    public List<DeductionVo> getDeduction(DeductionDto deductionDto) {
        log.info("参数校验1...");
        if(Objects.isNull(deductionDto)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        log.info("参数校验2...");
        if(Objects.isNull(deductionDto.getStartTime()) || Objects.isNull(deductionDto.getEndTime())) {
            throw new IllegalArgumentException("属性不能为空");
        }
        String tableName = getTableName(deductionDto.getStartTime());

        Deduction deduction = new Deduction();
        deduction.setTableName(tableName);
        // 属性拷贝
        BeanUtils.copyProperties(deductionDto, deduction);
        return mapper.queryDeductionData(deduction);
    }

    /**
     * 获取表名: t_deduction_year_month
     * @param startTime 开始时间
     * @return 表名
     */
    private String getTableName(LocalDate startTime) {
        int year = startTime.getYear();
        int month = startTime.getMonthValue();
        return String.format("t_deduction_%d_%d", year, month);
    }
}
