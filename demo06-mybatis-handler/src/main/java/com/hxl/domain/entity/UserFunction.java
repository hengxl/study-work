package com.hxl.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user_function")
public class UserFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("user_id")
    private Long userId;

    @Column("meeting")
    private List<String> meeting;
}
