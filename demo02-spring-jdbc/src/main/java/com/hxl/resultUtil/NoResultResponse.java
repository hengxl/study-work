package com.hxl.resultUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 无返回
 *
 * @author hunter
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NoResultResponse implements Response {
    INSTANCE(0, "success");

    private final int resCode;
    private final String resMessage;

    NoResultResponse(int resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    @Override
    public int getResCode() {
        return resCode;
    }

    @Override
    public String getResMessage() {
        return resMessage;
    }
}
