package com.hxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxl.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

@Mapper
@Repository // 标记为持久层
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    Page<User> selectPageVo(Page<User> page, BigDecimal balance);
}
