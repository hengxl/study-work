package com.hxl.local;

import com.alibaba.fastjson.JSONArray;
import com.hxl.local.domain.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class RestTemplateTest {

    @Value("${wechat.base-url}")
    private String baseUrl;

    @Value("${wechat.path}")
    private String path;

    @Value("${wechat.app-code}")
    private String appCode;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getUser() {
        String url = baseUrl.concat(String.format("/user" +
                "?username=%s", "李四"));
        String responseBody = Optional.ofNullable(
                        restTemplate.getForObject(url, String.class))
                .orElse(StringUtils.EMPTY);
        JSONObject resultJson = JSON.parseObject(responseBody);
        JSONArray scores = resultJson.getJSONArray("scores");
        System.out.println(scores);
        System.out.println(resultJson.toJSONString());
    }

    @Test
    public void get() {
        String url = baseUrl + "/get";
        String responseBody = Optional.ofNullable(
                        restTemplate.getForObject(url, String.class))
                .orElse(StringUtils.EMPTY);
        JSONObject resultJson = JSON.parseObject(responseBody);

        Integer code = (Integer) resultJson.get("code");
        System.out.println("code: " + code);

        String msg = (String) resultJson.get("msg");
        System.out.println("massage: " + msg);

        Map<String, String> map = (Map<String, String>) resultJson.get("data");
        String sessionKey = map.get("session_key");
        System.out.println("session_key: " + sessionKey);
        String openid = map.get("openid");
        System.out.println("openid: " + openid);
    }

    @Test
    public void post() {
        String jsCode = "js-code";
        String url = baseUrl + path;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestBody body = new RequestBody();
        body.setJsCode(jsCode);
        body.setAppCode(appCode);

        HttpEntity<RequestBody> entityParam = new HttpEntity<>(body, headers);
        // 调用接口
        String responseBody = Optional.ofNullable(
                        restTemplate.postForObject(url, entityParam, String.class))
                .orElse(StringUtils.EMPTY);
        // 转换为json格式
        JSONObject resultJson = JSON.parseObject(responseBody);

        int code = resultJson.getIntValue("code");
        if (code != 200) {
            throw new RuntimeException("请求失败！！！");
        }

        String msg = resultJson.getString("msg");
        System.out.println("massage: " + msg);

        JSONObject data = resultJson.getJSONObject("data");
        String openid = data.getString("openid");
        String sessionKey = data.getString("session_key");
        System.out.println("openid: " + openid);
        System.out.println("sessionKey: " + sessionKey);
    }

}
