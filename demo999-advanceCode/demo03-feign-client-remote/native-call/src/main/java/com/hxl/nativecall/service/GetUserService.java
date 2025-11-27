package com.hxl.nativecall.service;

import com.hxl.domain.model.Response;
import com.hxl.nativecall.domain.entity.User;

public interface GetUserService {

    Response<User> getUser(Long userId);
}
