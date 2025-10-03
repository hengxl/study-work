package com.hxl.consumer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void test() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4);

        // TODO：实现加法
        multiCalculate(numList, list -> {
            Integer result = 0;
            for (Integer num : list) {
                result += num;
            }
            System.out.println("执行加法：" + result);
        });

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------");

        // TODO: 实现乘法
        multiCalculate(numList, list -> {
            Integer result = 1;
            for (Integer num : list) {
                result *= num;
            }
            System.out.println("执行乘法：" + result);
        });
    }


    /**
     * 自定义多功能计算功能
     */
    private void multiCalculate(List<Integer> numList, Consumer<List<Integer>> consumer) {
        consumer.accept(numList);
    }
}
