package com.hxl.mapper;

import com.hxl.configuraion.MapResultHandler;
import com.hxl.domain.entity.UserFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface HandlerMapper {

    void insert(Integer userId, List<String> meeting);

    Optional<UserFunction> selectByUserId(Integer userId);

    List<String> selectString(String code);

    /**
     * 查询用户的会议功能权限 并且以code形式返回
     */
    Optional<UserFunction> selectFuncSetByUserId(Integer userId);

    /**
     * 查询多个用户的会议功能权限 封装成Map并且以code形式返回
     */
    void selectFuncMap(@Param("userIds") List<Integer> userIds, @Param("resultHandler") MapResultHandler<Integer, Set<String>> resultHandler);
}
