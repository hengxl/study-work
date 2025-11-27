package com.hxl.manager;

import com.hxl.manager.base.domain.entity.User;
import com.hxl.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class MultiModuleTest {

    @Resource
    private UserService userService;

    @Test
    public void multiModuleAutowiredTest() {
        log.info("Bean注入: {}", userService);
        User user = userService.getUser();
        System.out.println("获取user信息: " + user);
    }
}
