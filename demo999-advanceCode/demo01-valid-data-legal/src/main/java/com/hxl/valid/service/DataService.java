package com.hxl.valid.service;

import java.util.Map;

public interface DataService {

    /**
     * 检查 id 是否存在在数据库里 （高性能）
     * @param id id值
     */
    void checkExist(Integer id);

    /**
     * 校验授权名单, 并且转换成 Map<username, authType> 结构
     * @param authStr 授权名单字符串: 例如 "hxl,2#jsj,3"
     */
    Map<String, String> convertAndCheckAuthStr(String authStr);
}
