package com.hxl;

import com.hxl.entity.UserInfo;
import com.hxl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    @Resource
    private UserService userService;

    /**
     * 用户信息为 null
     */
    @Test
    public void test01() {
        UserInfo userInfo = null;
        userService.query(userInfo);
    }

    /**
     * 用户信息无效
     */
    @Test
    public void test02() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(10000000L);
        userInfo.setUsername("hxl");
        userService.query(userInfo);
    }
}
