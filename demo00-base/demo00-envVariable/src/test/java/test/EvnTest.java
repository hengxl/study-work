package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EvnTest {

    @Value("${hst.ces.log.enable:true}")
    private boolean autoLogEnable;

    public boolean isAutoLogEnable() {
        return autoLogEnable;
    }

    @Test
    public void test01() {
        System.out.println(isAutoLogEnable());
    }

}
