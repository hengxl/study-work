package com.hxl.service.impl;

import com.hxl.constant.ErrorEnum;
import com.hxl.constant.ExceptionConst;
import com.hxl.entity.UserInfo;
import com.hxl.exception.BusinessException;
import com.hxl.exception.ParamsException;
import com.hxl.mapper.UserMapper;
import com.hxl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void query(UserInfo userInfo) {
        // 校验传递参数
        System.out.println("====== 开始传递参数校验 =======");
        if (Objects.isNull(userInfo)) {
            throw new BusinessException(ExceptionConst.USER_INFO_NULL,
                    ExceptionConst.getMsgByCode(ExceptionConst.USER_INFO_NULL));
        }

        // 校验有效性
        System.out.println("====== 开始有效性校验 =======");
        boolean isValid = validateParam(userInfo);
        ParamsException.isTrue(isValid, ErrorEnum.PARAM_INVALID);

        System.out.println("====== 开始空指针校验 =======");
        String phone = userMapper.queryPhone(userInfo.getUserId());
        // 模拟空指针
        System.out.println(phone.toLowerCase());
    }

    private boolean validateParam(UserInfo userInfo) {
        if (userInfo.getUserId() != 2200130841L) {
            return false;
        }

        if (userInfo.getUsername().length() >= 5) {
            return false;
        }
        return true;
    }
}
