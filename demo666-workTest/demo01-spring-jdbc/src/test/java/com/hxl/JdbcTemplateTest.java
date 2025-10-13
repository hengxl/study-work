package com.hxl;

import com.hxl.domain.entity.User;
import com.hxl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    /**
     * TODO: 测试驼峰转换
     *  1.表字段Phone，实体类字段phone
     *  2.表字段Phone，sql语句phone
     */
    @Test
    public void test04() {
        Long id = 2L;
        String sql = "select Phone from my_user where id = ?";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        List<User> query = jdbcTemplate.query(sql, mapper, id);
        User user = DataAccessUtils.singleResult(query);
        System.out.println(user);

        System.out.println("======================================");

        sql = "select phone from my_user where id = ?";
        List<String> infoList = jdbcTemplate.queryForList(sql, String.class, id);
        String info = infoList.isEmpty() ? null : infoList.get(0);
        System.out.println(info);
    }
}
