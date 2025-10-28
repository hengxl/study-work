package com.hxl.service.impl;

import com.hxl.constant.ExceptionCodeConstant;
import com.hxl.exception.BusinessException;
import com.hxl.mapper.TransactionMapper;
import com.hxl.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper mapper;

    @Override
    public void add(Integer userId, Integer productId, Integer number) {
        // BigDecimal balance = mapper.queryBalanceByUserId(userId);
        BigDecimal balance = null;
        if (Objects.isNull(balance)) {
            throw new BusinessException(
                    ExceptionCodeConstant.USER_NO_EXIST,
                    ExceptionCodeConstant.getMsgByCode(ExceptionCodeConstant.USER_NO_EXIST));
        }
    }
}
