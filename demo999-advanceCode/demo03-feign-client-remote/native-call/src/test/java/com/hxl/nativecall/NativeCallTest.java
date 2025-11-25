package com.hxl.nativecall;

import com.hxl.domain.model.Response;
import com.hxl.nativecall.domain.entity.User;
import com.hxl.nativecall.service.GetUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NativeCallTest {

    @Autowired
    private GetUserService userService;

    @Test
    public void testGetUser() {
        Response<User> userResponse = userService.getUser(123456L);
        System.out.println(userResponse);
    }
}
