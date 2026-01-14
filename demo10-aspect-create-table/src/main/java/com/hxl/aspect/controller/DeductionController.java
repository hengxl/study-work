package com.hxl.aspect.controller;

import com.hxl.aspect.entity.StatisticsDto;
import com.hxl.aspect.entity.DeductionVo;
import com.hxl.aspect.service.DeductionService;
import com.hxl.aspect.service.OnlineNumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户消费控制器
 *
 * @author hxl
 */
@RestController
@Slf4j
public class DeductionController {

    @Resource
    private DeductionService service;

    @Resource
    private OnlineNumService onlineNumService;

    @GetMapping("/getDeduction")
    public List<DeductionVo> getDeduction(StatisticsDto statisticsDto) {
        log.info("deductionDto:{}", statisticsDto);
        return service.getDeduction(statisticsDto);
    }

    @GetMapping("/getOnlineNum")
    public Integer getOnlineNum(StatisticsDto statisticsDto) {
        log.info("onlineNumDto:{}", statisticsDto);
        return onlineNumService.getOnlineNum(statisticsDto);
    }
}
