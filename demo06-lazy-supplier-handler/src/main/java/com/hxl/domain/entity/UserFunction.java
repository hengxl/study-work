package com.hxl.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user_function")
public class UserFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @TableField("user_id")
    private Long userId;

    @TableField("meeting")
    private Set<String> meeting;
}
