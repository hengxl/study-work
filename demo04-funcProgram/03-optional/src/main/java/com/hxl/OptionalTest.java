package com.hxl;


import org.junit.Test;

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
