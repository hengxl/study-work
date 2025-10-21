package com.hxl.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class User {

    private Long id;

    private String username;

    private String password;

    // TODO: 数据库里是Phone 驼峰不会有问题
    private String phone;

    private String info;

    private Byte status;

    private BigDecimal balance;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}
