package com.hxl;

import com.hxl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    private UserService userService;

    /**
     * 测试queryForObjectTest
     */
    @Test
    public void test01() {
        userService.queryForObjectTest();
    }

    /**
     * 测试queryTest
     */
    @Test
    public void test02() {
        userService.queryTest();
    }

    /**
     * 测试queryForListTest
     */
    @Test
    public void test03() {
        userService.queryForList();
    }
}
