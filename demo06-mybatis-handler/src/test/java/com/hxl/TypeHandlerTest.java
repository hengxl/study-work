package com.hxl;

import com.hxl.configuraion.MapResultHandler;
import com.hxl.domain.entity.UserFunction;
import com.hxl.mapper.TypeHandlerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class TypeHandlerTest {

    @Autowired
    private TypeHandlerMapper typeHandlerMapper;

    /**
     * 新增、修改
     */
    @Test
    public void test01() {
        // 给我创建可变的List<String>集合，并且里面有3个元素
        List<String> list = Arrays.asList("2", "3");
        typeHandlerMapper.insert(1, list);
    }

    /**
     * 查询 List<String>
     */
    @Test
    public void test02() {
        Optional<UserFunction> typeHandler = typeHandlerMapper.selectByUserId(7);

        typeHandler.ifPresent(t -> t.getMeeting().forEach(System.out::println));
    }

    /**
     * 批量查询 Map<Integer, List<String>>
     */
    @Test
    public void test03() {
        List<Integer> userList = Arrays.asList(2, 3, 6, 8);
        MapResultHandler<Integer, List<String>> mapResult = new MapResultHandler<>();

        typeHandlerMapper.selectUserMeetingFunctionList(userList, mapResult);
        Map<Integer, List<String>> userFuncList = mapResult.getMappedResults();
        userFuncList.forEach((userId, funcList) -> System.out.println(userId + ":::" + funcList));
    }


}
