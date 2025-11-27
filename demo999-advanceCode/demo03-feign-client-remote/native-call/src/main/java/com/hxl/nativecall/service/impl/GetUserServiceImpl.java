package com.hxl.nativecall.service.impl;

import com.hxl.domain.model.Response;
import com.hxl.nativecall.domain.entity.User;
import com.hxl.nativecall.service.GetUserService;
import com.hxl.nativecall.service.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserServiceImpl implements GetUserService {

    /**
     * TODO: 在SpringCloud里，通过@FeignClient注解，可以自动为接口生成实现类，这样子就可以成功依赖注入！
     */
    @Autowired
    private UserRemoteService remoteService;

    /**
     * 获取用户数据
     */
    @Override
    public Response<User> getUser(Long userId) {
        return remoteService.getUserRemote(userId).getResult();
    }
}
