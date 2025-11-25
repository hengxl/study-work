package com.hxl.domain.util;


import com.hxl.domain.constant.CommonConst;
import org.springframework.util.function.SingletonSupplier;

import java.util.function.Supplier;

/**
 * 返回消息方法类
 *
 * @author sqdai
 * @date 2019/6/25 15:29
 **/
public abstract class ResultUtil {
    /**
     * 无结果成功对象单例
     */
    public static final Supplier<Result<Object>> SUCCESS =
            SingletonSupplier.of(() -> new Result<>(CommonConst.ZERO_STR, CommonConst.SUCCESS_STR, null));

    private ResultUtil() {
    }

    /**
     * 获取正常的返回结果
     *
     * @return Result
     */
    public static Result success() {
        return SUCCESS.get();
    }

    /**
     * 获取正常的返回结果
     *
     * @param data 数据
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(CommonConst.ZERO_STR, CommonConst.SUCCESS_STR, data);
    }

    /**
     * 获取正常的返回结果
     *
     * @param msg  消息
     * @param data 数据
     * @return resultCode
     */
    public static Result success(String msg, Object data) {
        return new Result<>(CommonConst.ZERO_STR, msg, data);
    }

    /**
     * 构建失败结果
     *
     * @param msg  错误消息
     * @param data 数据体
     * @return Result
     */
    public static Result buildFailure(String code, String msg, Object data) {
        return new Result<>(code, msg, data);
    }

    public static Result buildFailure(String code, String msg) {
        return new Result<>(code, msg, null);
    }

}
