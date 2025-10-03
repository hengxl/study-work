package com.hxl.reflect;

import java.lang.reflect.Field;

public class ObjectReflectUtil {

    // 自定义 通过反射 打印对象属性的方法
    public static void printObjectFiled(Object obj) {
        if (obj == null) System.out.println("对象为NULL");

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                // 突破访问权限
                field.setAccessible(true);

                // 获取字段名和对应的值
                String fieldName = field.getName();
                Object fieldValue = field.get(obj); // 获取vo实例中该字段的值

                if (fieldValue == null) continue;

                // 5. 输出结果
                System.out.println(fieldName + " = " + fieldValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}