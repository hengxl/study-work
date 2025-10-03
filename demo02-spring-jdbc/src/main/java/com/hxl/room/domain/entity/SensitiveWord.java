package com.hxl.room.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Table("t_sensitive_word") // 小心导错包
public class SensitiveWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("company_id")
    private Long companyId;

    // 敏感词 空格分隔
    @Column("words")
    private String words;

    @CreatedDate
    @Column("create_time")
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column("update_time")
    private LocalDateTime updateTime;
}
