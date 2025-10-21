package com.hxl.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user_function")
public class UserFunc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @TableField("user_id")
    private Integer userId;

    @TableField("meeting")
    private String meeting;
}
