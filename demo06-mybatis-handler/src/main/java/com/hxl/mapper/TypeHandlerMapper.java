package com.hxl.mapper;

import com.hxl.configuraion.MapResultHandler;
import com.hxl.domain.entity.UserFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TypeHandlerMapper {

    void insert(Integer userId, List<String> meeting);

    Optional<UserFunction> selectByUserId(Integer userId);

    void selectUserMeetingFunctionList(@Param("userList") List<Integer> userList, MapResultHandler<Integer, List<String>> mapResult);
}
