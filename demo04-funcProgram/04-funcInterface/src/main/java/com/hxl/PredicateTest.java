package com.hxl;

import com.hxl.domain.dto.AccountDTO;
import com.hxl.domain.vo.AccountVO;
import org.junit.Test;

import java.util.function.Predicate;

public class PredicateTest {

    /**
     * 利用Predicate来条件判断
     */
    public AccountDTO predicateStatus(AccountVO vo, Predicate<String> predicate) {
        AccountDTO dto = new AccountDTO()
                .setName(vo.getUsername());

        dto.setActive(predicate.test(vo.getStatusCode()));

        return dto;
    }

    @Test
    public void test() {
        AccountVO vo = new AccountVO()
                .setUsername("好视通")
                .setStatusCode("已激活");

        AccountDTO dto = predicateStatus(vo,
                s -> vo.getStatusCode().equals("已激活"));

        System.out.println(dto);
    }
}
