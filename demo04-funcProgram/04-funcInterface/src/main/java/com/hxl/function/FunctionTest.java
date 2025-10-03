package com.hxl.function;

import com.hxl.domain.entity.Account;
import com.hxl.domain.vo.AccountVO;
import org.junit.Test;

import java.util.function.Function;

public class FunctionTest {

    /**
     * 函数式接口：将status转换为对应的code
     */
    public AccountVO i18nConvertStatus(Account list, Function<Integer, String> function) {
        String code = function.apply(list.getStatus());

        return new AccountVO()
                .setUsername(list.getUsername())
                .setStatusCode(code);
    }

    // 国际化实现！
    @Test
    public void testForChinese() {
        Account chinese = new Account().setUsername("中国人").setPassword("123456").setStatus(1);
        AccountVO vo = i18nConvertStatus(chinese, integer -> {
            if (integer == 1) {
                return "第一";
            } else if (integer == 2) {
                return "第二";
            } else {
                return "第三";
            }
        });

        System.out.println(vo);

        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");

        Account english = new Account().setUsername("bug").setPassword("123456").setStatus(2);

        vo = i18nConvertStatus(english, integer -> {
            if (integer == 1) {
                return "one";
            } else if (integer == 2) {
                return "two";
            } else {
                return "three";
            }
        });

        System.out.println(vo);
    }
}

