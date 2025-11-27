package com.hxl.domain.response.base;

import com.hxl.domain.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private int resCode = CommonConstant.RES_SUCCESS;

    private String resMessage = CommonConstant.RES_SUCCESS_MESSAGE;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

}
