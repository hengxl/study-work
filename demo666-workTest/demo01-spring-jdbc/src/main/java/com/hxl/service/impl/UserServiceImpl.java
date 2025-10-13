package com.hxl.service.impl;

import com.hxl.domain.entity.User;
import com.hxl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * queryForObject方法，当查询不到数据时，抛出异常，而不会返回null
     * EmptyResultDataAccessException
     */
    @Override
    public void queryForObjectTest() {
        Long id = 100L;
        String sql = "select * from my_user where id = ?";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, mapper, id);
        System.out.println(user);
    }

    /**
     * query方法，当查询不到数据时，返回null
     */
    @Override
    public void queryTest() {
        Long id = 1L;
        String sql = "select * from my_user where id = ?";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query(sql, mapper, id);
        // 优化方案1
        User user = users.isEmpty() ? null : users.get(0);
        System.out.println(user);

        System.out.println("============================================");

        // 优化方案2
        user = DataAccessUtils.singleResult(users);
        System.out.println(user);
    }


    @Override
    public void queryForList() {
        Long id = 2L;
        String sql = "select info from my_user where id = ?";
        List<String> infoList = jdbcTemplate.queryForList(sql, String.class, id);
        // 优化方案1
        String info = infoList.isEmpty() ? null : infoList.get(0);
        System.out.println(info);

        System.out.println("===========================");

        // 优化方案2
        info = DataAccessUtils.singleResult(infoList);
        System.out.println(info);
    }


}
