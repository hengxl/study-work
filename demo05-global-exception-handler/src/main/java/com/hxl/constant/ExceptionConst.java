package com.hxl.constant;

import com.google.common.collect.ImmutableMap;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 异常代码常量
 */
@NoArgsConstructor
public final class ExceptionConst {
    /**
     * BusinessException 相关错误码
     */
    public static final Integer USER_INFO_NULL = 10001;
    public static final Integer USER_NO_EXIST = 10002;
    public static final Integer USER_NAME_INVALID = 10003;

    /**
     * ServiceException 相关错误码
     */
    public static final Integer REQUEST_TIME_OUT = 20001;

    // 错误信息映射
    private static final Map<Integer, String> CODE_MSG = ImmutableMap.of(
            USER_INFO_NULL, "business.user.info.null",
            USER_NO_EXIST, "business.not.user.exist",
            USER_NAME_INVALID, "business.user.name.invalid",
            REQUEST_TIME_OUT, "service.request.time.out"
    );

    public static String getMsgByCode(Integer code) {
        return CODE_MSG.get(code);
    }
}
