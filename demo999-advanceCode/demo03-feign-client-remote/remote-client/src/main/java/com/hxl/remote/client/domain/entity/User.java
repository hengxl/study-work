package com.hxl.remote.client.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Integer userId;

    private String username;

    private Integer age;

    private List<Friend> friends;
}
