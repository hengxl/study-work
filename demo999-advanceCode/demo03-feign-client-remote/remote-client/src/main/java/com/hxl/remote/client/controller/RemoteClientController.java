package com.hxl.remote.client.controller;

import com.hxl.domain.model.Response;
import com.hxl.remote.client.domain.entity.Friend;
import com.hxl.remote.client.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RemoteClientController {

    @GetMapping("/remote/client/get")
    public Response<User> getUser(@RequestParam("userId") Integer userId) {
        Response<User> response = new Response<>();
        log.info("用户ID: {}", userId);

        User user = new User();
        user.setUserId(userId);
        user.setUsername("用户" + userId);
        user.setAge(20);

        List<Friend> friends = new ArrayList<>(128);
        Friend friend = new Friend();
        friend.setName("张三");
        friend.setAge(18);
        friends.add(friend);
        friend = new Friend();
        friend.setName("李四");
        friend.setAge(22);
        friends.add(friend);

        user.setFriends(friends);
        response.setCode(200);
        response.setMsg("调用成功！");
        response.setResult(user);
        return response;
    }
}
