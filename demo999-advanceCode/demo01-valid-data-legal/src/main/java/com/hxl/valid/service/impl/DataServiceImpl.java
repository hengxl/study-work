package com.hxl.valid.service.impl;

import com.hxl.valid.mapper.DataMapper;
import com.hxl.valid.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public void checkExist(Integer id) {
        dataMapper.existById(id)
                .orElseThrow(() -> new RuntimeException("id not exist!"));
        log.info("id : {}", id);
    }

    @Override
    public void checkAuthStr(String authStr) {
        // 校验authStr
        if (!StringUtils.hasText(authStr)) {
            throw new RuntimeException("error illegal argument");
        }

        String[] items = authStr.split("#");
        // 使用Steam流 + 正则,校验每一个items的合法性: username,authType
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]{1,32},[0234]$");
        boolean hasInvalidItem = Arrays.stream(items)
                .anyMatch(item -> !pattern.matcher(item).matches());
        if (hasInvalidItem) {
            throw new RuntimeException("error illegal argument");
        }
        log.info("hasInvalidItem: {}", hasInvalidItem);
    }
}
