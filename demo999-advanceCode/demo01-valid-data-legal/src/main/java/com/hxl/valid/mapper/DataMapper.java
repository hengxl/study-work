package com.hxl.valid.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface DataMapper {

    Optional<Integer> existById(@Param("id") Integer id);
}
