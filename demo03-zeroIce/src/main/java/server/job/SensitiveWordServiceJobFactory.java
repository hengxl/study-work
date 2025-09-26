package server.job;

import com.hxl.server.MyMgr.AMD_SensitiveWordService_GetSensitiveWord;
import com.hxl.server.MyMgr.AMD_SensitiveWordService_TestToList;
import com.hxl.server.constants.Constants;
import com.hxl.server.model.SensitiveWordModel;
import com.hxl.server.serviceInterface.ISensitiveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class SensitiveWordServiceJobFactory {

    private final Logger log = LoggerFactory.getLogger(SensitiveWordServiceJobFactory.class);
    public ISensitiveWordService wordService;

    public AbstractJob getTestToListJob(AMD_SensitiveWordService_TestToList amdTestToList, int companyId) {

        return new AbstractJob() {
            @Override
            public void execute() {
                List<String> list = null;

                try {

                    list = Arrays.asList("hxl", "and", "jsj");

                } catch (Exception ex) {
                    setResult(Constants.EXCEPTION);
                    log.error("getTestToListJob error ", ex);
                }

                amdTestToList.ice_response(getResult(), list);
            }
        };
    }


    public AbstractJob getGetSensitiveWordJob(AMD_SensitiveWordService_GetSensitiveWord amdGetSensitiveWord, int roomId) {

        return new AbstractJob() {
            @Override
            public void execute() {

                SensitiveWordModel model = null;

                try {

                    model = wordService.getSensitiveWords(roomId);

                } catch (Exception ex) {
                    setResult(Constants.EXCEPTION);
                    log.error("getSensitiveWordJob error ", ex);
                }

                amdGetSensitiveWord.ice_response(getResult(), model);
            }
        };
    }
}
