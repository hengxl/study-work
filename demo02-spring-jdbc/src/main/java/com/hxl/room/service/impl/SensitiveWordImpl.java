package com.hxl.room.service.impl;


import com.hxl.room.domain.entity.SensitiveWord;
import com.hxl.room.repository.SensitiveWordRepository;
import com.hxl.room.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SensitiveWordImpl implements SensitiveWordService {


    @Autowired
    SensitiveWordRepository sensitiveWordRepository;

    @Override
    public List<String> getWords(Long companyId) throws RuntimeException {

//        String words = Optional.ofNullable(sensitiveWordMapper.getSensitiveWords(companyId))
//                .orElse("");

        String words = Optional.ofNullable(sensitiveWordRepository.getSensitiveWords(companyId))
                .orElse("");

        // 去除首尾空格
        words = words.trim();

        return words.isEmpty() ? Collections.emptyList() : Arrays.asList(words.split(" +"));
    }

    @Override
    public void saveWords(Long companyId, String words) {
        // 查询是否存在旧配置
        SensitiveWord configInfo = sensitiveWordRepository.getSensitiveWordsInfo(companyId);

        if (configInfo == null) {
            configInfo = new SensitiveWord()
                    .setCompanyId(companyId);
        } else {
            configInfo.setUpdateTime(null);
        }

        // 设置必要的公共属性
        configInfo.setWords(words);

        // id为 null 则插入，否则更新
        sensitiveWordRepository.save(configInfo);
    }
}
