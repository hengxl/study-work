package com.hxl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hxl.domain.entity.User;
import com.hxl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void dome01() {
        User user = userMapper.selectById(2);
        System.out.println("user = " + user);
    }

    @Test
    public void domo02() {
        Map<String, Object> userInfo = userMapper.selectMapById(3L);

        System.out.println("userInfo = " + userInfo);
    }

    @Test
    public void demo03() {
        User user = new User().setUsername("测试2")
                .setStatus(1)
                .setPassword("123456")
                .setBalance(BigDecimal.valueOf(1000.0))
                .setInfo("测试用户2")
                .setPhone("13113094123");

        int row = userMapper.insert(user);

        System.out.println(row);
    }

    /**
     * 组合条件查询
     */
    @Test
    public void demo04() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("balance", 4300.50).
                like("phone", "8");

        List<User> users = userMapper.selectList(wrapper);

        users.forEach(System.out::println);
    }

    /**
     * lambda表示优先级查询
     */
    @Test
    public void demo05() {
        // 将id > 12 并且 balance <= 5000 或 username 包含 "i" 的  info 和 password修改
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("id", 12)
                .and(i -> i.le("balance", 5000.0).or().like("username", i));

        User user = new User().setInfo("太jb牛逼了").setPassword("1111111111");

        userMapper.update(user, wrapper);
    }

    /**
     * 指定某些字段进行查询
     */
    @Test
    public void demo06() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username", "phone");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);

        System.out.println(maps);
    }

    /**
     * 子查询
     */
    @Test
    public void demo07() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询 id > 12 的用户信息
        wrapper.inSql("id", "select id from my_user where id > 12");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 修改语句的方式：
     * 1. Entity 存数据  +  QueryWrapper 存条件
     * 2. UpdateWrapper 存数据 和 条件
     */
    @Test
    public void demo08() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        // 将id > 12 并且 balance <= 5000 或 username 包含 "i" 的  info 和 password修改
        wrapper.gt("id", 12)
                .and(i -> i.le("balance", 5000.0).or().like("username", i));

        // 将 info 字段修改为 "太tmd牛逼了" ； 将 password 字段修改为 "222222222"
        wrapper.set("info", "太tmd牛逼了").set("password", "222222222");

        int row = userMapper.update(null, wrapper);
        System.out.println(row);
    }

    /**
     * 条件构造器
     */
    @Test
    public void demo09() {
        String username = "a";
        Long begin = 20L;
        Long end = null;

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询 username 包含 #{username} 并且  id > begin  and  id < end
        wrapper.like(StringUtils.isNotBlank(username), "username", username)
                .gt(begin != null, "id", begin)
                .lt(end != null, "id", end);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * Lambda Query条件构造器！！！
     */
    @Test
    public void demo10() {
        String username = "a";
        Long begin = 20L;
        Long end = null;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), User::getUsername, username)
                .gt(begin != null, User::getId, begin)
                .lt(end != null, User::getId, end);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * Lambda Update条件构造器！！！
     */
    @Test
    public void demo11() {
        // 类比 Lambda Query条件构造器
    }



}
