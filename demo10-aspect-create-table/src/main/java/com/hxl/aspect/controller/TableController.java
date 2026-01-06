package com.hxl.aspect.controller;

import com.hxl.aspect.entity.TableOne;
import com.hxl.aspect.entity.TableTow;
import com.hxl.aspect.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class TableController {

    @Resource
    private TableService service;

    @GetMapping("/getOne")
    public List<TableOne> getTableOne(@RequestParam("tableName") String tableName) {
        return service.getTableOne(tableName);
    }

    @GetMapping("/getTwo")
    public List<TableTow> getTableTwo(@RequestParam("tableName") String tableName) {
        return service.getTableTow(tableName);
    }
}
