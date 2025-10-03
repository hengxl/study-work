package com.hxl.supplier;

import org.junit.Test;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierTest {

    // 供给型接口
    Supplier<Integer> supplier = () -> new Random().nextInt(10) + 1;

    @Test
    public void test() {
        // 需求是 获取一个 1 ~ 10 的随机数
        Integer random = supplier.get();
        System.out.println(random);
    }

    @Test
    public void testLazyLoad() {
        Lazy<String> lazyString = new Lazy<>(() -> {
            System.out.println("Initializing lazy string...");
            return "Hello, World!";
        });

        /**
         * 由于 value 变量只会被初始化一次
         * 因此只有在第一次调用 lazyString.get() 方法时会输出 "Initializing lazy string..."
         * 后续调用时不会输出。
         */
        System.out.println(lazyString.get());
        System.out.println(lazyString.get());
        System.out.println(lazyString.get());
    }
}
