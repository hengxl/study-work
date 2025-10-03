package com.hxl.server.serviceInterface;

import com.hxl.server.model.SensitiveWordModel;
import com.hxl.server.register.ICEService;

public interface ISensitiveWordService extends ICEService {

    /**
     * 获取敏感词配置信息
     * @param roomId 会议id
     * @return 敏感词配置信息
     */
    SensitiveWordModel getSensitiveWords(int roomId);
}
