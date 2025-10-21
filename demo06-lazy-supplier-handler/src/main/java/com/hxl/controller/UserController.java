package com.hxl.controller;

import com.hxl.constant.FunctionType;
import com.hxl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 测试懒加载：第一次调用时，会懒加载一次。之后再调用则直接从缓存获取
     */
    @GetMapping("/lazy")
    public void getUser() {
        List<String> allFuncList = FunctionType.getAllFuncList();
        List<String> allFunctionCodeList = FunctionType.getAllFunctionCodeList();
        String allFuncString = FunctionType.getAllFuncString();
        log.info("==================================================");
        log.info(allFuncList.toString());
        log.info(allFunctionCodeList.toString());
        log.info(allFuncString);
    }

    /**
     * 测试权限Map，例如
     * {
     *   "1000000001": "1,2,4",
     *   "1000000002": "2,4"
     * }
     */
    @GetMapping("pri")
    public Map<Integer, String> getPrivileges() {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(1000000001);
        userIds.add(1000000002);
        return userService.getPriByUserIds(userIds);
    }

    /**
     * 测试权限功能Code,例如
     * userId：1000000002
     * [
     *   "online_call",
     *   "meeting_now"
     * ]
     */
    @GetMapping("/code")
    public List<String> getPriCode(@RequestParam("userId") Integer userId) {
        return userService.getPriCodeByUserId(userId);
    }
}
