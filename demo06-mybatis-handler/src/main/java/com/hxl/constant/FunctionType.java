package com.hxl.constant;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.ArrayList;

/**
 * 功能类型工具类
 * 使用不可变Map存储数字类型与功能代码的映射关系
 */
public class FunctionType {
    // 构造函数私有化
    private FunctionType() {
    }

    // 使用ImmutableMap构建不可变缓存
    private static final ImmutableMap<String, String> TYPE_TO_CODE =
            ImmutableMap.of("1", "online_call", "2", "meeting_now", "4", "schedule_meeting");

    // 使用Supplier实现懒加载缓存
    private static final Supplier<List<String>> ALL_FUNC_LIST = createLazySupplier(() -> {
        System.out.println("=================Init load Func List=================");
        return new ArrayList<>(TYPE_TO_CODE.keySet());
    });

    private static final Supplier<List<String>> ALL_FUNC_CODES = createLazySupplier(() -> {
        System.out.println("=================Init load Func Codes=================");
        return new ArrayList<>(TYPE_TO_CODE.values());
    });

    private static final Supplier<String> ALL_FUNC_STRING = createLazySupplier(() -> {
        System.out.println("=================Init load Func String=================");
        return String.join(",", TYPE_TO_CODE.keySet());
    });

    /**
     * 创建懒加载Supplier
     */
    private static <T> Supplier<T> createLazySupplier(Supplier<T> supplier) {
        return new Supplier<T>() {
            private T value;
            @Override
            public T get() {
                if (value == null) {
                    value = supplier.get();
                }
                return value;
            }
        };
    }

    /**
     * 根据数字类型获取对应的功能代码
     * @param type 数字类型
     * @return 功能代码
     */
    public static String getCodeByType(String type) {
        return TYPE_TO_CODE.get(type);
    }

    /**
     * 获取所有数字类型列表 例如：["1", "2", "4"]
     */
    public static List<String> getAllFuncList() {
        return ALL_FUNC_LIST.get();
    }

    /**
     * 获取所有数字类型的字符串，用逗号分隔
     * 例如："1,2,4"
     * @return 数字类型字符串
     */
    public static String getAllFuncString() {
        return ALL_FUNC_STRING.get();
    }

    /**
     * 获取所有功能代码列表 例如：["online_call", "meeting_now", "schedule_meeting"]
     */
    public static List<String> getAllFunctionCodeList() {
        return ALL_FUNC_CODES.get();
    }
}