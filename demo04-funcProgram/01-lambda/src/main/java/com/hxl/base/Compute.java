package com.hxl.base;

/**
 * 函数式接口就是一个 有且只有一个抽象方法 的接口
 */

// 判断当前接口是否是函数式接口
@FunctionalInterface
public interface Compute {

    public abstract void computer(Integer a, Integer b);

}
