package com.hxl.valid;

import com.hxl.valid.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@SpringBootTest
public class DataValidTest {

    @Resource
    private DataService dataService;

    @Test
    public void checkExistTest() {
        try {
            dataService.checkExist(3);
        } catch (Exception e) {
            log.error("method checkExist error", e);
            throw e;
        }
    }

    @Test
    public void checkAuthStr() {
        try {
            Map<String, String> map = dataService.convertAndCheckAuthStr("衡_Xl,2#jsj,0");
            // 遍历输出
            map.forEach((username, authType) ->
                    System.out.println("====> " + username + " -> " + authType));
        } catch (Exception e) {
            log.error("method checkAuthStr error", e);
            throw e;
        }
    }
}
