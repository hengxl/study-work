package com.hxl.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
@TableName("my_user") // 指定表名
public class User {

    // 将属性所对应的字段指定为主键 即便字段名叫其它名字都没有影响
//    @TableId(value = "id", type = IdType.AUTO) // 默认是雪花算法
    private Long id;

    private String username;

    private String password;

    private String info;

    private Integer status;

    // 工资
    private BigDecimal balance;

    // 手机号
    private String phone;
}
