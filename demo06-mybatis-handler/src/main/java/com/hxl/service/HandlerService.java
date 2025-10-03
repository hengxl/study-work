package com.hxl.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HandlerService {
    /**
     * 获取用户的功能权限
     */
    List<String> getFunction(Integer userId);

    /**
     * 获取多个用户的功能权限code
     */
    Map<Integer, Set<String>> getFuncByUsers(List<Integer> userIds);
}
