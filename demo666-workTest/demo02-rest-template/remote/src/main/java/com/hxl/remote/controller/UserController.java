package com.hxl.remote.controller;

import com.hxl.remote.domain.dto.RestDTO;
import com.hxl.remote.domain.vo.UserVO;
import com.hxl.remote.domain.entity.User;
import com.hxl.remote.domain.vo.DataVO;
import com.hxl.remote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public User getUser(@RequestParam("username") String name) {
        return service.getUser(name);
    }

    @PostMapping("/user")
    public User getUser(@RequestBody UserVO vo) {
        return service.postUser(vo);
    }

    @GetMapping("/get")
    public DataVO getData() {
        DataVO vo = new DataVO();
        vo.setCode(0);
        vo.setMsg("success");
        Map<String, String> map = new HashMap<>(128);
        map.put("openid", "12345");
        map.put("session_key", "abcde");
        vo.setData(map);
        return vo;
    }

    @PostMapping("/post")
    public DataVO postData(HttpServletRequest request,
                           @RequestBody RestDTO dto) {
        String jsCode = dto.getJsCode();
        if (Objects.isNull(jsCode)) {
            throw new RuntimeException("jsCode为空!");
        }

        String appCode = dto.getAppCode();
        if (Objects.isNull(appCode)) {
            throw new RuntimeException("appCode为空!");
        }

        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (!"application/json".equals(contentType)) {
            throw new RuntimeException("内容协商不匹配!");
        }

        DataVO vo = new DataVO();
        vo.setCode(200);
        vo.setMsg("成功了");
        Map<String, String> map = new HashMap<>(128);
        map.put("openid", "abcde");
        map.put("session_key", "hij");
        vo.setData(map);

        return vo;
    }
}
