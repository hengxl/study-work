package com.hxl.remote.mapper;

import com.hxl.remote.domain.entity.Score;
import com.hxl.remote.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User queryOne(@Param("u") User user);

    List<Score> getScore(@Param("uName") String username, @Param("sName") String scoreName);
}
