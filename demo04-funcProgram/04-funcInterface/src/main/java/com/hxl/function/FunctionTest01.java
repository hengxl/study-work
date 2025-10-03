package com.hxl.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionTest01 {
    /**
     * 列表转字符串
     */
    public Function<List<?>, String> listToString() {
        return list -> list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("_"));
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("1", "2", "3");

        String apply = listToString().apply(list);

        System.out.println(apply);
    }
}
