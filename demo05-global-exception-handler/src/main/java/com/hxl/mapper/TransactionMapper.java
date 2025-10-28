package com.hxl.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    /**
     * 新增商品
     * @param userId
     * @param productId
     * @param number
     */
    void add(Integer userId, Integer productId, Integer number);
}
