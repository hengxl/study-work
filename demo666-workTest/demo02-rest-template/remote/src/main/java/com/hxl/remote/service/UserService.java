package com.hxl.remote.service;

import com.hxl.remote.domain.vo.UserVO;
import com.hxl.remote.domain.entity.User;

public interface UserService {
    /**
     * 根据姓名获取信息
     */
    User getUser(String name);

    /**
     * 自定义
     */
    User postUser(UserVO vo);
}
