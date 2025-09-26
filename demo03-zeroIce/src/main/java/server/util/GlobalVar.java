package server.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Scott
 * @ClassName: GlobalVar
 * @Description: TODO
 * @date 2015年6月3日 下午4:10:09
 */
public class GlobalVar {

    private static final Logger logger = LoggerFactory.getLogger("metric");

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final String METRIC_NAME = "boss.ice.request";

    private String serviceId;

    private String deviceId;

    private int samplePeriod = 15;

    private String platform;

    private String ip;

    private Map<String, Object> lables;

    public static int requestNumbers = 0;

    public static int rejectNumbers = 0;

    public static int receiveNumbers = 0;

    public static int logRequestNumbers = 0;

    public static int logRejectNumbers = 0;

    public static int logReceiveNumbers = 0;

    static final Timer timer = new Timer();

    public GlobalVar() {

        startTimer();
    }

    /**
     * 定时器
     */
    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                logger.info(GlobalVar.this.buildMetric());

                GlobalVar.this.reset();
            }
        }, 10 * 1000L, samplePeriod * 1000L);
    }

    private String buildMetric() {
        Map<String, Object> mrequest = new HashMap<>();
        mrequest.put("name", METRIC_NAME);
        mrequest.put("timestamp", System.currentTimeMillis());


        if (this.lables != null && this.lables.size() > 0) {
            mrequest.put("labels", this.lables);
        } else {
            this.lables = new HashMap<>();
            this.lables.put("serviceId", serviceId);
            this.lables.put("deviceId", deviceId);
            this.lables.put("platform", platform);
            this.lables.put("ip", ip);

            mrequest.put("labels", this.lables);
        }

        Map<String, Object> fields = new HashMap<>();
        fields.put("requestNumbers", requestNumbers);
        fields.put("requestNumbers", requestNumbers);
        fields.put("receiveNumbers", receiveNumbers);
        fields.put("logRequestNumbers", logRequestNumbers);
        fields.put("logRejectNumbers", logRejectNumbers);
        fields.put("logReceiveNumbers", logReceiveNumbers);
        mrequest.put("fields", fields);

        String metric = GSON.toJson(mrequest);

        return metric;
    }

    private void reset() {
        requestNumbers = 0;
        receiveNumbers = 0;
        rejectNumbers = 0;
        logRequestNumbers = 0;
        logReceiveNumbers = 0;
        logRejectNumbers = 0;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setSamplePeriod(int samplePeriod) {
        this.samplePeriod = samplePeriod;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
