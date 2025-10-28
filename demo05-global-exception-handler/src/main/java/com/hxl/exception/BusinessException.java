package com.hxl.exception;

import lombok.Getter;

/**TODO:
 *   业务异常 (BusinessException)
 *   定位：具体的业务规则违反
 *   场景：用户操作不符合业务逻辑
 *   示例：
 *     1.余额不足
 *     2.库存不够
 *     3.重复提交
 *     4.权限不足
 *   特点：预期内的、可恢复的、需要明确提示用户
 */
@Getter
public class BusinessException extends BaseException {
    /**
     * TODO: 必须在构造器实例化阶段赋值
     */
    private final Integer code;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
