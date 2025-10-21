package com.hxl.service.impl;

import com.hxl.constant.FunctionType;
import com.hxl.hande.MapResultHandler;
import com.hxl.mapper.UserMapper;
import com.hxl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<Integer, String> getPriByUserIds(List<Integer> userIds) {
        MapResultHandler<Integer, String> mapResultHandler = new MapResultHandler<>();
        userMapper.selectFuncMapByUserIds(userIds, mapResultHandler);
        return mapResultHandler.getMappedResults();
    }

    @Override
    public List<String> getPriCodeByUserId(Integer userId) {
        // 查询为null则默认有全部权限
        return userMapper.selectFuncSetByUserId(userId)
                .map(userFunction -> new ArrayList<>(userFunction.getMeeting()))
                .orElseGet(() -> new ArrayList<>(FunctionType.getAllFunctionCodeList()));
    }
}
