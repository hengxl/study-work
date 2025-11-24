package com.hxl.optional;

import com.hxl.optional.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OptionalTest {

    /**
     *  Optional.ofNullable()
     *  1.如果传入的值不是 null，则返回一个包含该值的 Optional 对象。
     *  2.如果传入的值是 null，则返回一个自定义的 Optional 对象。
     *    另外，最好结合orElseGet()懒加载来优化内存
     */
    @Test
    public void test01() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setUserId(1 + i);
            user.setUsername("name" + (1 + i));
            userList.add(user);
        }

        Map<Integer, User> userMap =
                Optional.ofNullable(userList)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .collect(Collectors.toMap(
                                User::getUserId,
                                Function.identity()
                        ));
        System.out.println(userMap);

        System.out.println("===========================================");
        // ================== 顺带测试foreach ========================
        List<Integer> userIds = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userIds.add(i);
        }
        userIds.forEach(userid -> {
            if (userid % 2 == 0) {
                return;
            }
            System.out.println(userid);
        });
    }
}
