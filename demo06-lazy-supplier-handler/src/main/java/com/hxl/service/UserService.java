package com.hxl.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 根据用户ID集合获取对应的权限Map
     */
    Map<Integer, String> getPriByUserIds(List<Integer> userIds);

    /**
     * 根据用户ID获取对应的功能Code集合
     */
    List<String> getPriCodeByUserId(Integer userId);
}
