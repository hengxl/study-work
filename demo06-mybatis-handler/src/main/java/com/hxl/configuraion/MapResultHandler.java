package com.hxl.configuraion;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hengxiaolaing
 * @version 1.0
 * @description: key-value方式的mybatis结果处理器
 * @date 2025/09/19 11:58
 */
public class MapResultHandler<K, V> implements ResultHandler {

    private final Map<K, V> mappedResults = new HashMap<>();

    @Override
    public void handleResult(ResultContext context) {
        Map<K, V> map = (Map) context.getResultObject();
        mappedResults.put((K) map.get("key"), map.get("value"));
    }

    public Map<K, V> getMappedResults() {
        return mappedResults;
    }
}
