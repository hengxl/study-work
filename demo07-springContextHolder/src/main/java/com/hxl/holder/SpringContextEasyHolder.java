package com.hxl.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.function.SingletonSupplier;

import java.util.function.Supplier;

/**
 * Spring上下文持有工具类
 * 用于在非Spring管理的类中获取Bean
 */
@Component
public class SpringContextEasyHolder implements ApplicationContextAware {

    // 静态变量保存Spring应用上下文
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口，Spring启动时自动注入上下文
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextEasyHolder.applicationContext = applicationContext;
    }

    /**
     * 获取指定类型的Bean Supplier（延迟获取）
     */
    public static <T> Supplier<T> getSupplier(Class<T> clazz) {
        return SingletonSupplier.of(() -> applicationContext.getBean(clazz));
    }

    /**
     * 获取指定名称和类型的Bean Supplier（延迟获取）
     */
    public static <T> Supplier<T> getSupplier(Class<T> clazz, String beanName) {
        return SingletonSupplier.of(() -> applicationContext.getBean(beanName, clazz));
    }

    /**
     * 检查Spring容器中是否包含指定名称的Bean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
}