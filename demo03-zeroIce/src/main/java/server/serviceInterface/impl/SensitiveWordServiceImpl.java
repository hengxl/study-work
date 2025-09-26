package server.serviceInterface.impl;

import com.hxl.server.model.SensitiveWordModel;
import com.hxl.server.rpc.SensitiveWordRpcService;
import com.hxl.server.serviceInterface.ISensitiveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SensitiveWordServiceImpl implements ISensitiveWordService {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordServiceImpl.class);

    @Autowired
    private SensitiveWordRpcService sensitiveWordRpcService;
    @Override
    public SensitiveWordModel getSensitiveWords(int roomId) {

        // 写死
        Integer companyId = roomId;

        return sensitiveWordRpcService.getSensitiveWords(Long.valueOf(companyId));
    }
}
