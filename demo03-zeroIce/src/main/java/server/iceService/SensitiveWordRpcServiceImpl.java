package server.iceService;

import Ice.Current;
import com.hxl.server.MyMgr.AMD_SensitiveWordService_GetSensitiveWord;
import com.hxl.server.MyMgr.AMD_SensitiveWordService_TestToList;
import com.hxl.server.MyMgr._SensitiveWordServiceDisp;
import com.hxl.server.constants.Constants;
import com.hxl.server.job.SensitiveWordServiceJobFactory;
import com.hxl.server.util.WorkQueue;

public class SensitiveWordRpcServiceImpl extends _SensitiveWordServiceDisp {

    private static final long serialVersionUID = 1L;

    private SensitiveWordServiceJobFactory serviceJobFactory;

    @Override
    public void TestToList_async(AMD_SensitiveWordService_TestToList amdTestToList, int companyId, Current __current) {
        if (WorkQueue.isBusy()) {
            amdTestToList.ice_response(Constants.SYSTEM_BUSY, null);
            return;
        }

        WorkQueue.addJob(serviceJobFactory.getTestToListJob(amdTestToList, companyId));
    }

    @Override
    public void GetSensitiveWord_async(AMD_SensitiveWordService_GetSensitiveWord amdGetSensitiveWord, int roomId, Current __current) {
        if (WorkQueue.isBusy()) {
            amdGetSensitiveWord.ice_response(Constants.SYSTEM_BUSY, null);
            return;
        }

        WorkQueue.addJob(serviceJobFactory.getGetSensitiveWordJob(amdGetSensitiveWord, roomId));
    }

    public void setSensitiveWordJobFactory(SensitiveWordServiceJobFactory serviceJobFactory) {
        this.serviceJobFactory = serviceJobFactory;
    }

}
