package com.hxl.valid.service.impl;

import com.hxl.valid.mapper.DataMapper;
import com.hxl.valid.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    /**
     * 预编译正则表达式,减少重复编译带来的性能开销
     */
    private static final Pattern authStrPatter
            = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]{1,32},[0234]$");

    @Resource
    private DataMapper dataMapper;

    @Override
    public void checkExist(Integer id) {
        dataMapper.existById(id)
                .orElseThrow(() -> new RuntimeException("id not exist!"));
        log.info("id : {}", id);
    }

    @Override
    public Map<String, String> convertAndCheckAuthStr(String authStr) {
        // 校验authStr
        if (!StringUtils.hasText(authStr)) {
            throw new RuntimeException("error illegal argument");
        }

        String[] items = authStr.split("#");
        // 使用Steam流 + 预编译正则表达式,校验每一个items的合法性: username,authType
        boolean hasInvalidItem = Arrays.stream(items)
                .anyMatch(item -> !authStrPatter.matcher(item).matches());
        if (hasInvalidItem) {
            throw new RuntimeException("error illegal argument");
        }
        log.info("hasInvalidItem: {}", hasInvalidItem);

        // 转换为Map结构
        return Arrays.stream(items)
                .map(item -> item.split(","))
                .collect(Collectors.toMap(
                        split -> split[0],
                        split -> split[1]));
    }
}
