package com.hxl.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.function.SingletonSupplier;

import java.util.function.Supplier;

/**
 * TODO：SpringContextHolder类似Spring容器(通常是BeanFactory的增强类ApplicationContext)的通行证
 *  当一个普通类，不被Spring容器管理，但是它又想获取Spring容器中的Bean对象
 *  此时它就可以通过SpringContextHolder获取Spring容器中的Bean对象
 * <p>
 * Spring上下文持有类，用于获取Spring容器中的Bean
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    // 静态变量，保存Spring容器上下文实例 TODO: 类加载时就已经初始化
    private static ApplicationContext applicationContext;

    /**
     * Spring容器初始化时自动调用，注入上下文实例 （加载需要时间！可能慢于静态变量）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * TODO：自己实现一个懒加载的Supplier，第一次调用的时候才会初始化
     *  可以确保此时 Spring容器应该已经初始化完成
     *  避免了在静态变量初始化时，Spring容器还没有初始化完成，导致获取Bean失败的错误
     */
    public static <T> Supplier<T> getSupplier(Class<T> clazz) {
        return SingletonSupplier.of(() -> applicationContext.getBean(clazz));
    }

    /**
     * 同上 只是获取方式不同
     */
    public static <T> Supplier<T> getSupplier(Class<T> clazz, String beanName) {
        return SingletonSupplier.of(() -> applicationContext.getBean(beanName, clazz));
    }
}