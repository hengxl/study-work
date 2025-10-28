package com.hxl.constant;

import com.google.common.collect.ImmutableMap;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 异常代码常量
 */
@NoArgsConstructor
public final class ExceptionCodeConstant {
    /**
     * BusinessException相关错误码
     */
    public static final Integer USER_NO_EXIST = 10001;
    public static final Integer BALANCE_NO_ENOUGH = 10002;
    public static final Integer PRODUCT_NO_STOCK = 10003;

    /**
     * ServiceException相关错误码
     */
    public static final Integer REQUEST_TIME_OUT = 20001;

    // 错误信息映射
    private static final Map<Integer, String> CODE_MSG = ImmutableMap.of(
            USER_NO_EXIST, "business.not.user.exist",
            BALANCE_NO_ENOUGH, "business.not.enough.balance",
            PRODUCT_NO_STOCK, "business.not.product.stock",
            REQUEST_TIME_OUT, "service.request.time.out"
    );

    public static String getMsgByCode(Integer code) {
        return CODE_MSG.get(code);
    }
}
