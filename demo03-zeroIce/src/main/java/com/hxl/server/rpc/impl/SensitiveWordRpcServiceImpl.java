package com.hxl.server.rpc.impl;

import com.hxl.server.domain.entity.SensitiveWord;
import com.hxl.server.exception.ServiceException;
import com.hxl.server.model.SensitiveWordModel;
import com.hxl.server.repository.SensitiveWordRepository;
import com.hxl.server.rpc.SensitiveWordRpcService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DubboService
public class SensitiveWordRpcServiceImpl implements SensitiveWordRpcService {

    @Autowired
    private SensitiveWordRepository sensitiveWordRepository;

    @Override
    public SensitiveWordModel getSensitiveWords(Long companyId) throws ServiceException {

        SensitiveWord info = sensitiveWordRepository.getSensitiveWordsInfo(companyId);

        if (info == null) return new SensitiveWordModel(companyId, Collections.emptyList());

        String words = Optional.ofNullable(info.getWords())
                .orElse("");

        // 去除首尾空格
        words = words.trim();
        List<String> wordList = words.isEmpty() ? Collections.emptyList() : Arrays.asList(words.split(" +"));

        return new SensitiveWordModel(info.getCompanyId(), wordList);
    }
}
