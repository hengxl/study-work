package com.hxl.dm.mapper;

import com.hxl.dm.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmMapper {

    List<User> queryAll();
}
