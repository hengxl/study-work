package server;

import Ice.Communicator;
import Ice.Util;
import com.hxl.server.iceService.SensitiveWordRpcServiceImpl;
import com.hxl.server.job.SensitiveWordServiceJobFactory;
import com.hxl.server.serviceInterface.impl.SensitiveWordServiceImpl;
import lombok.var;

public class ServerMain {
    public static void main(String[] args) {
        Communicator communicator = null;
        try {
            // 1. 初始化 Ice 通信器
            communicator = Util.initialize(args);

            // 2. 创建服务适配器（绑定端口 10000）
            var adapter = communicator.createObjectAdapterWithEndpoints(
                    "SensitiveWordServiceAdapter",
                    "tcp -h 0.0.0.0 -p 10001"
            );

            // 3. 初始化业务依赖链
            SensitiveWordServiceImpl wordService = new SensitiveWordServiceImpl(); // 实际业务实现
            SensitiveWordServiceJobFactory jobFactory = new SensitiveWordServiceJobFactory();
            jobFactory.wordService = wordService; // 设置业务服务到工厂（需在 SensitiveWordServiceJobFactory 中添加 set 方法或直接赋值）

            // 4. 实例化 RPC 服务并注入工厂
            SensitiveWordRpcServiceImpl rpcService = new SensitiveWordRpcServiceImpl();
            rpcService.setSensitiveWordJobFactory(jobFactory); // 注入工厂

            // 5. 注册服务到适配器
            adapter.add(rpcService, Util.stringToIdentity("SensitiveWordService"));

            // 6. 激活适配器，开始提供服务
            adapter.activate();
            System.out.println("服务端启动成功，监听端口 10000...");

            // 7. 等待关闭信号
            communicator.waitForShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (communicator != null) {
                communicator.destroy(); // 释放资源
            }
        }
    }
}