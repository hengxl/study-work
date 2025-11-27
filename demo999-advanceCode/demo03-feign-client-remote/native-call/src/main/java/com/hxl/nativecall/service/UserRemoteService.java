package com.hxl.nativecall.service;

import com.hxl.domain.model.Response;
import com.hxl.nativecall.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 如果在微服务里，接口前就不能写死为具体的ip
 * 而是在 @FeignClient里声明对应服务名为 client
 * => client/remote/client/get
 */
//@FeignClient(name = "client")
public interface UserRemoteService {

    @GetMapping("localhost:8001//remote/client/get")
    Response<Response<User>> getUserRemote(Long userId);
}
