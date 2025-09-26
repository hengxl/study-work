package server.rpc;

import com.hxl.server.exception.ServiceException;
import com.hxl.server.model.SensitiveWordModel;
import com.hxl.server.register.RpcService;

public interface SensitiveWordRpcService extends RpcService {
    /**
     * 获取敏感词配置信息
     * @param companyId 公司id
     * @return 敏感词配置信息
     */
    SensitiveWordModel getSensitiveWords(Long companyId) throws ServiceException;
}
