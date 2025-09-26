package com.hxl;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    @Test
    public void test01() {
        User user = Optional.ofNullable(get01()).orElse(new User());
        Optional<User> optional = Optional.ofNullable(get01());
    }

    private User get01() {
//        return null;
        return new User().setName("张三").setAge(18);
    }
}
