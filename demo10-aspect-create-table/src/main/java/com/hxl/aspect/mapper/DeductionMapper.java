package com.hxl.aspect.mapper;

import com.hxl.aspect.entity.Deduction;
import com.hxl.aspect.entity.DeductionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeductionMapper {

    /**
     * 查询用户扣除数据
     * @param deduction 查询参数
     * @return 用户扣除数据
     */
//    @EnableFillCalendar
    List<DeductionVo> queryDeductionData(Deduction deduction);
}
