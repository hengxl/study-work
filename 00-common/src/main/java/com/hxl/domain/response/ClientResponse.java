package com.hxl.domain.response;

import com.hxl.domain.constant.CommonConstant;
import com.hxl.domain.response.base.BaseResponse;

/**
 * 客户端使用返回结果
 */
public class ClientResponse extends BaseResponse {
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     */
    public static ClientResponse ok(Object result) {
        ClientResponse response = new ClientResponse();
        response.setResCode(CommonConstant.RES_SUCCESS);
        response.setResMessage(CommonConstant.RES_SUCCESS_MESSAGE);
        response.setResult(result);
        return response;
    }

    /**
     * 失败
     */
    public static ClientResponse fail(int code, String message) {
        ClientResponse response = new ClientResponse();
        response.setResCode(code);
        response.setResMessage(message);
        return response;
    }
}
