package com.hxl.enums;

/**
 * 功能类型枚举
 * 用于定义数字代码与功能名称的映射关系
 */
public enum FunctionType {
    ONLINE_CALL("1", "online_call"),
    MEETING_NOW("2", "meeting_now"),
    SCHEDULE_MEETING("4", "schedule_meeting");

    private final String code;
    private final String name;

    FunctionType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据数字代码获取对应的功能名称
     * @param code 数字代码
     * @return 功能名称，如果未找到匹配则返回原始代码
     */
    public static String getNameByCode(String code) {
        for (FunctionType type : FunctionType.values()) {
            if (type.getCode().equals(code)) {
                return type.getName();
            }
        }
        return code; // 如果未找到匹配，返回原始代码
    }

    /**
     * 根据功能名称获取对应的数字代码
     * @param name 功能名称
     * @return 数字代码，如果未找到匹配则返回原始名称
     */
    public static String getCodeByName(String name) {
        for (FunctionType type : FunctionType.values()) {
            if (type.getName().equals(name)) {
                return type.getCode();
            }
        }
        return name; // 如果未找到匹配，返回原始名称
    }
}