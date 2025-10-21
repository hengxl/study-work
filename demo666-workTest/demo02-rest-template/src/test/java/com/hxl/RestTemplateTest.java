package com.hxl;

import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class RestTemplateTest {

    @Test
    public void test(){
        List<Integer> list1 = null;
        List<String> list2 = new ArrayList<>();

        System.out.println(CollectionUtils.isEmpty(list1));
        System.out.println(CollectionUtils.isEmpty(list2));
    }

}
