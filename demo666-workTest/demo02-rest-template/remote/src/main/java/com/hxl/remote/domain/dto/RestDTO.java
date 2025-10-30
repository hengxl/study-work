package com.hxl.remote.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestDTO {

    @NotBlank(message = "code不能为空")
    private String jsCode;

    @NotBlank(message = "appCode不能为空")
    private String appCode;
}
