package com.hxl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxl.domain.entity.User;
import com.hxl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class MybatisPlusPageTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void demo01() {
        Page<User> page = new Page<>(2, 4);
        userMapper.selectPage(page, null);
        // 获取查询到的记录
        List<User> records = page.getRecords();

        // 获取总页数
        long pages = page.getPages();

        // 获取当前页码
        long current = page.getCurrent();

        // 获取总记录数
        long total = page.getTotal();

        long size = page.getSize();

        records.forEach(System.out::println);

        System.out.println("总页数：" + pages);
        System.out.println("当前页码：" + current);
        System.out.println("总记录数：" + total);
        System.out.println("每页显示的记录数：" + size);
    }

    /**
     * 自定义方法分页
     */
    @Test
    public void demo02() {
        Page<User> page = new Page<>(2, 4);
        userMapper.selectPageVo(page, BigDecimal.valueOf(4000));
        // 获取查询到的记录
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
    }
}
