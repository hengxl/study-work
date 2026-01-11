package com.hxl.aspect.controller;

import com.hxl.aspect.entity.DeductionDto;
import com.hxl.aspect.entity.DeductionVo;
import com.hxl.aspect.service.DeductionService;
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

    @GetMapping("/get")
    public List<DeductionVo> getDeduction(DeductionDto deductionDto) {
        log.info("deductionDto:{}", deductionDto);
        return service.getDeduction(deductionDto);
    }

}
