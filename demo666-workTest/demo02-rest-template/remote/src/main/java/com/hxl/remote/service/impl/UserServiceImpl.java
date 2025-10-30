package com.hxl.remote.service.impl;

import com.hxl.remote.domain.vo.UserVO;
import com.hxl.remote.domain.entity.Score;
import com.hxl.remote.domain.entity.User;
import com.hxl.remote.mapper.UserMapper;
import com.hxl.remote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUser(String name) {
        User wrapper = new User();
        wrapper.setName(name);
        User user = mapper.queryOne(wrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在！");
        }

        List<Score> scores = mapper.getScore(name, null);

        user.setScores(scores);
        return user;
    }

    @Override
    public User postUser(UserVO vo) {
        String username = vo.getUsername();
        String scoreName = vo.getScoreName();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(scoreName)) {
            throw new RuntimeException("信息不全！！！");
        }

        User user = mapper.queryOne(new User().setName(username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在！！！");
        }
        List<Score> scores = mapper.getScore(username, scoreName);
        user.setScores(scores);
        return user;
    }
}
