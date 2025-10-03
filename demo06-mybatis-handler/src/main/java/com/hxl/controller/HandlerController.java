package com.hxl.controller;

import com.hxl.bean.response.ClientResponse;
import com.hxl.constant.FunctionType;
import com.hxl.service.HandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
public class HandlerController {

    @Autowired
    private HandlerService handlerService;

    /**
     * 测试Supplier懒加载
     */
    @GetMapping("/supplier/lazy1")
    public ClientResponse lazy1() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFuncList());
        System.out.println(FunctionType.getAllFuncList());

        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFuncString());
        System.out.println(FunctionType.getAllFuncString());

        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFunctionCodeList());
        System.out.println(FunctionType.getAllFunctionCodeList());
        return ClientResponse.ok(null);
    }

    /**
     * 测试Supplier懒加载
     */
    @GetMapping("/supplier/lazy2")
    public ClientResponse lazy2() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFuncList());

        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFuncString());

        System.out.println("---------------------------------------------------------------------");
        System.out.println(FunctionType.getAllFunctionCodeList());
        return ClientResponse.ok(null);
    }

    /**
     * 查询用户功能权限
     */
    @GetMapping(value = "/user/functions")
    public ClientResponse getFuncByUser(@Param("userId") Integer userId) {

        return ClientResponse.ok(handlerService.getFunction(userId));
    }

    /**
     * 测试多用户的TypeHandler
     */
    @GetMapping(value = "/users/functions")
    public ClientResponse getFuncByUsers(@RequestParam("userIds") List<Integer> userIds) {
        Map<Integer, Set<String>> mapResult = handlerService.getFuncByUsers(userIds);

        return ClientResponse.ok(mapResult);
    }
}
