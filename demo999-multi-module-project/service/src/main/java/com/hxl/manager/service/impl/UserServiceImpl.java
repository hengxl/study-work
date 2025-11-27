package com.hxl.manager.service.impl;

import com.hxl.manager.base.constant.CommonConst;
import com.hxl.manager.base.domain.entity.User;
import com.hxl.manager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setUserId(666L);
        user.setUsername(CommonConst.USER_NAME);
        return user;
    }
}
