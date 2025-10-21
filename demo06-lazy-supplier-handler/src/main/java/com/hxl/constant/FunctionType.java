package com.hxl.constant;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.String.join;

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

    // 使用Guava的Suppliers.memoize实现懒加载缓存
    private static final Supplier<List<String>> ALL_FUNC_LIST =
            Suppliers.memoize(() -> {
                System.out.println("all_func_list 加载了...");
                return new ArrayList<>(TYPE_TO_CODE.keySet());
            });

    private static final Supplier<List<String>> ALL_FUNCTION_CODES =
            Suppliers.memoize(() -> {
                System.out.println("all_function_codes 加载了...");
                return new ArrayList<>(TYPE_TO_CODE.values());
            });

    private static final Supplier<String> ALL_FUNC_STRING =
            Suppliers.memoize(() -> {
                System.out.println("all_func_string 加载了...");
                return join(",", TYPE_TO_CODE.keySet());
            });


    /**
     * 根据数字类型获取对应的功能代码
     * 例如： 1 -> "online_call"
     */
    public static String getCodeByType(String type) {
        return TYPE_TO_CODE.get(type);
    }

    /**
     * 获取所有数字类型列表
     * 例如：["1", "2", "4"]
     */
    public static List<String> getAllFuncList() {
        return ALL_FUNC_LIST.get();
    }

    /**
     * 获取所有数字类型的字符串，用逗号分隔
     * 例如："1,2,4"
     */
    public static String getAllFuncString() {
        return ALL_FUNC_STRING.get();
    }

    /**
     * 获取所有功能代码列表
     * 例如：["online_call", "meeting_now", "schedule_meeting"]
     */
    public static List<String> getAllFunctionCodeList() {
        return ALL_FUNCTION_CODES.get();
    }
}