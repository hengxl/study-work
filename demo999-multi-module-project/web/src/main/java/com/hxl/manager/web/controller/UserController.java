package com.hxl.manager.web.controller;

import com.hxl.manager.base.domain.entity.User;
import com.hxl.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/multi/user/get")
    public User getUser() {
        User user = userService.getUser();
        log.info("user is : {}", user);
        return user;
    }
}
