package com.hxl.dm;

import com.hxl.dm.domain.entity.User;
import com.hxl.dm.mapper.DmMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DMTest {

    @Autowired
    private DmMapper mapper;

    @Test
    public void test01() {
        List<User> list = mapper.queryAll();
        list.forEach(System.out::println);
    }
}
