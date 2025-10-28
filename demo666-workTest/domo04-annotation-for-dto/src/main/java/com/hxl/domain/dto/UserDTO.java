package com.hxl.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @NotNull(message = "username not null")
    private String username;

    @Max(value = 20, message = "age to big")
    private Integer age;

    @Size(max = 5, message = "number max length is 5")
    private String number;
}
