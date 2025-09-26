package server;

import Ice.Communicator;
import Ice.Util;
import com.hxl.server.MyMgr.SensitiveWordServicePrx;
import com.hxl.server.MyMgr.SensitiveWordServicePrxHelper;
import com.hxl.server.model.SensitiveWordModelHolder;
import lombok.var;

public class ClientMain {
    public static void main(String[] args) {
        Communicator communicator = null;
        try {
            // 1. 初始化 Ice 通信器
            communicator = Util.initialize(args);

            // 2. 获取服务代理（地址对应服务端）
            String proxyStr = "SensitiveWordService:tcp -h 127.0.0.1 -p 10001";
            var basePrx = communicator.stringToProxy(proxyStr);
            SensitiveWordServicePrx servicePrx = SensitiveWordServicePrxHelper.checkedCast(basePrx);
            if (servicePrx == null) {
                throw new RuntimeException("获取服务代理失败");
            }

            // 3. 调用远程方法（同步调用）
            int roomId = 1001; // 示例会议ID
            SensitiveWordModelHolder resultHolder = new SensitiveWordModelHolder(); // 接收返回结果
            int status = servicePrx.GetSensitiveWord(roomId, resultHolder);

            // 4. 处理返回结果
            if (status == 0) { // 假设 0 为成功状态码
                System.out.println("获取敏感词成功：" + resultHolder.value.getWordList());
            } else {
                System.out.println("调用失败，状态码：" + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (communicator != null) {
                communicator.destroy(); // 释放资源
            }
        }
    }
}