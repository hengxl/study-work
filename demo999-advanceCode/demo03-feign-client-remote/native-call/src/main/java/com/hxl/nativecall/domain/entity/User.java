package com.hxl.nativecall.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Long userId;

    private String username;

    private Integer age;

    private List<Friend> friends;
}
