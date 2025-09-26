package com.hxl.base;

import org.junit.jupiter.api.Test;

public class Demo01 {
    /**
     * 定义一个计算方法
     * TODO：当参数类型为接口时，传入的必须是该接口的实现类！！！
     *      比如匿名内部类就是其中一种实现类
     */
    public void computed(Compute compute) {
        compute.computer(3, 6);
    }


    /**
     * 常规
     */
    @Test
    public void test01() {
        computed(new Compute() {
            @Override
            public void computer(Integer a, Integer b) {
                System.out.println(a + b);
            }
        });
    }

    /**
     * 完整Lambda
     */
    @Test
    public void test02() {
        computed(
                (Integer a, Integer b) -> {
                    System.out.println(a + b);
                }
        );
    }

    /**
     * 简化Lambda
     * TODO:
     *  1.参数类型可以省略
     *  2.如果只有一个参数，()可以省略
     *  3.如果方法体只有一行代码，{}，分号，和return都可以省略，但必须同时省略
     */
    @Test
    public void test03() {
        computed(
                (a, b) -> System.out.println(a + b)
        );
    }
}
