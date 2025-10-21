package com.hxl;

import com.hxl.constant.FunctionType;
import com.hxl.domain.entity.UserFunc;
import com.hxl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MapLazySupplierTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 测试某个权限类型是否已经存在，例如
     * meeting = '1,4'
     * 判断 '2'、'4'是否在里面
     */
    @Test
    public void test01() {
        String type = "1";
        List<UserFunc> userFunctions = userMapper.queryByFuncType(type);
        System.out.println("========================================================================");
        System.out.println(userFunctions);
        System.out.println("========================================================================");

        type = "2";
        userFunctions = userMapper.queryByFuncType(type);
        System.out.println("========================================================================");
        System.out.println(userFunctions);
        System.out.println("========================================================================");

        type = "3";
        userFunctions = userMapper.queryByFuncType(type);
        System.out.println("========================================================================");
        System.out.println(userFunctions);
        System.out.println("========================================================================");

        type = "4";
        userFunctions = userMapper.queryByFuncType(type);
        System.out.println("========================================================================");
        System.out.println(userFunctions);
        System.out.println("========================================================================");
    }

    /**
     * 查看用户是否有该会议室功能的权限
     * meetingType  1：通讯录呼叫 2：即时会议室  4：预约会议室
     */
    @Test
    public void hasMeetingFunction() {
        Integer userId = 1000000002;
        String meetingType = "4";
        String meetingCode = FunctionType.getCodeByType(meetingType);
        boolean result = userMapper.selectFuncSetByUserId(userId)
                .map(func -> func.getMeeting().contains(meetingCode))
                .orElse(true); // 没有查到任何数据，则返回true
        System.out.println(result);
    }

    @Test
    public void test() {
        String type = "1";
        String meeting = "11,21,41";
        System.out.println(meeting.contains(type)); // true
    }
}
