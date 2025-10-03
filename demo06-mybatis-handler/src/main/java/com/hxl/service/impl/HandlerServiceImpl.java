package com.hxl.service.impl;


import com.hxl.configuraion.MapResultHandler;
import com.hxl.constant.FunctionType;
import com.hxl.mapper.HandlerMapper;
import com.hxl.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HandlerServiceImpl implements HandlerService {

    @Autowired
    private HandlerMapper handlerMapper;

    @Override
    public List<String> getFunction(Integer userId) {
        return handlerMapper.selectFuncSetByUserId(userId)
                .map(userFunc -> new ArrayList<>(userFunc.getMeeting()))
                .orElseGet(() -> new ArrayList<>(FunctionType.getAllFunctionCodeList()));
    }

    @Override
    public Map<Integer, Set<String>> getFuncByUsers(List<Integer> userIds) {
        // 创建结果处理器TypeHandler
        MapResultHandler<Integer, Set<String>> TypeHandler = new MapResultHandler<>();

        handlerMapper.selectFuncMap(userIds, TypeHandler);

        return TypeHandler.getMappedResults();
    }
}