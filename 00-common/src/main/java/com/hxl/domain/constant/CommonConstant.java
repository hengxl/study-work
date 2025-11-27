package com.hxl.domain.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 公共常量
 *
 * @author Denny
 */
public interface CommonConstant {

    long SIGNATURE_EXPIRE_SECONDS = 60;

    public static final int RES_SUCCESS = 1;

    public static final String RES_SUCCESS_MESSAGE = "success";

    /**
     * 即时会议起始ID
     */
    public static final int ROOM_RUN_INFO_MIN_ROOMID = 1000000000;
    /**
     * 本地节点
     */
    public static final int LOCAL = 1;

    /**
     * 模糊匹配
     */
    public static final String USER_SEARCH_TYPE_LIKE = "0";

    /**
     * 模糊匹配
     */
    public static final String DEFAULT_SERACHTYPE_LIKE = "1";

    /**
     * 模糊查询的百分号
     */
    public static final String PERCENT_SIGN = "%";

    /**
     * token前缀
     */
    public static final String BEARER = "bearer ";

    /**
     * 默认当前页
     */
    public static final int CURRENT_PAGE = 1;

    /**
     * 默认分页条数
     */
    public static final int PAGE_SIZE = 15;

    /**
     * 人脸照片地址
     */
    public static final String FACE_IMAGE_ADDRESS = "faceImageAddress";

    // *********直播常量*************
    /**
     * 直播访问地址
     */
    public static final String LIVE_TOKEN = "liveToken";

    /**
     * 调用直播接口的token
     */
    public static final String LIVE_ACCESS_ADDRESS = "liveAccessAddress";

    /**
     * OPEN_AUTHORIZATION_FILTER 客户端缓存前缀
     */
    public static final String OPEN_AUTH_FILTER_PREFIX = "CES_AUTH2_CLIENT_S_";

    public static final String AUTH_SERVER_FILTER_PREFIX = "CES_R_A_F_";

    public static final String USER_ENTITY_TOKEN_PREFIX = "CES_R_A_U_T_";

    /**
     * 旁听
     */
    public static final String DEFAULT_USERRIGHT_AUDIT = "1";
    /**
     * 出席
     */
    public static final String DEFAULT_USERRIGHT_ATTEND = "2";
    /**
     * 主席
     */
    public static final String DEFAULT_USERRIGHT_CHAIRMAN = "3";

    /**
     * 默认在线
     */
    public static final Integer DEFAULT_USERRIGHT_ONLINE = 0;
    /**
     * 用户座次
     */
    public static final Integer DEFAULT_USERRIGHT_SEATLIST = 1000;

    /**
     * http协议
     */
    public static final String HTTP = "http";

    /**
     * https协议
     */
    public static final String HTTPS = "https";

    /**
     * 冒号 :
     */
    public static final String COLON = ":";

    /**
     * 冒号加双斜杠 ://
     */
    public static final String COLON_DOUBLESLASH = "://";


    public static final String CASCADE_USER_NAME = "userName";
    public static final String CASCADE_NODE_ID = "nodeId";
    public static final String CASCADE_HAVING = "having";
    public static final String CASCADE_QUERY_NODE_ID = "queryNodeId";
    public static final String CASCADE_CUR_PAGE = "curPage";
    public static final String CASCADE_PAGE_SIZE = "pageSize";
    public static final Integer CASCADE_CUR_PAGE_VALUE = 1;
    public static final Integer CASCADE_PAGE_SIZE_VALUE = 999999;
    public static final String CASCADE_PREFIX = "http://";

    /**
     * 级联服务业务接口地址
     */
    public static final String CASCADE_INTERFACE_HAVING_URL
            = "/meeting-resource-server/security/roomservice/having";

    public static final String CASCADE_INTERFACE_GET_ROOM_INFO
            = "/meeting-resource-server/security/roomservice/getroominfo";

    public static final Set<String> CASCADE_INTERFACE_LIST
            = new HashSet<>(Arrays.asList(CASCADE_INTERFACE_HAVING_URL, CASCADE_INTERFACE_GET_ROOM_INFO));

    public static final Set<String> CASCADE_METHOD_LIST
            = Stream.of(CASCADE_HAVING, "getroominfo").collect(Collectors.toSet());

    /**
     * 系统参数表的FSP应用ID
     */
    public static final String FSP_APP_ID = "fspAppId";
    /**
     * 系统参数表的FSP应用密钥
     */
    public static final String FSP_APP_SECRET = "fspAppSecret";

    /**
     * 后台paas通信地址
     */
    public static final String FSP_HTTP_ADDRESS = "fspHttpAddress";
}
