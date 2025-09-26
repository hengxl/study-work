/**
* Filename:    ResultConstants.java
* Description:
* Copyright:   Copyright (c)2015
* Company:     fsmeeting
* @author:     Scott
* @version:    1.0
* Create at:   2015年5月21日 上午11:05:22
*
* Modification History:
* Date           Author       Version      Description
* ------------------------------------------------------------------
* 2015年5月21日    Scott       1.0        1.0 Version
*/

package server.constants;

/**
 * @ClassName: ResultConstants
 * @Description: 返回结果常量定义类
 * @author Scott
 * @date 2015年5月21日 上午11:05:22 
 *
 */

public interface Constants {
    /**
     * 空结果
     */
    byte[] EMPTY_RESPONSE_RESULT = new byte[0];
	/**
	 * 数据库执行成功
	 */
	int SUCCESS = 0;

	/**
	 * result默认值
	 */
	int DEFAULT_RESULT = 0;

	/**
	 * 数据库执行异常
	 */
	int EXCEPTION = 4098;

	/**
	 * 系统繁忙，工作队列已满
	 */
	int SYSTEM_BUSY = 4106;

	/**
	 * 数据库返回为空
	 */
	int EMPTY_RESULT= 4107;

	/**
	 * 会议室密码错误
	 */
	int DB_RS_CLOUDMEETING_ROOM_PWD_ERROR = -3;

	/**
	 * 拒绝访问
	 */
	int DB_RS_CLOUDMEETING_ACCESSDENIED = -4;

	/**
	 * 房间未找到
	 */
	int DB_RS_CLOUDMEETING_ROOM_NOT_FOUND = -5;

    /**
     * 服务未找到
     */
    int DB_RS_CLOUDMEETING_SERVICE_NOT_FOUND = -10;

	/**
     * 会议室需要输入密码
     */
    int DB_RS_CLOUDMEETING_NEED_ROOM_PASSWORD = -11;

    /**
     * 会议室已过期
     */
    int DB_RS_CLOUDMEETING_ROOM_OVERTIME = -12;

    /**
     * 会议室已关闭
     */
    int DB_RS_CLOUDMEETING_ROOM_CLOSE = -13;

    /**
     * Token已存在
     */
    int DB_RS_TOKEN_EXIST = -15;

    /**
     * TOKEN重新激活
     */
    int DB_RS_TOKEN_REACTIVE = -16;

    /**
     * 组织授权点数已满
     */
    int DB_RS_ORG_LICENSE_FULL = -18;

    /**
     * 邀请码错误
     */
    int DB_RS_CLOUDMEETING_INVITECODE_ERROR = -21;

    /**
     * 邀请码已过期
     */
    int DB_RS_CLOUDMEETING_INVITECODE_EXPIRED = -22;

    /**
     * 未找到相应的设备列表
     */
    int DB_RS_CLOUDMEETING_TERMINALINFO_EMPTY = -23;

    /**
     * 未启用视频
     */
    int DB_RS_NOT_ENABLE_VIDEO = -24;

    /**
     * 未启用视频
     */
    int DB_RS_WX_ONLY_TRHREE = -25;

	/**
	 * 终端绑定的服务已过期
	 */
	int DB_RS_TERMINAL_SERVICE_EXPIRED = -26;

	/**
	 * 硬件终端不存在
	 */
	int DB_RS_TERMINAL_NOT_EXIST = -27;

    /**
     * 电话会议余额不足
     */
    int DB_RS_TELMEETING_HAS_NOT_ENOUGH_BALANCE = -30;

    /**
     * 企业购买的路数不够
     */
    int DB_RS_TELMEETING_ORG_NOT_ENOUGH_CALL_OUT = -31;

    /**
     * 话机所在网关剩余可呼路数不够
     */
    int DB_RS_TELMEETING_DEV_NOT_ENOUGH_CALL_OUT = -32;

	/**
	 * 未购买电话会议
	 */
	int DB_RS_TELMEETING_HAS_NOT_PHONE_MEETING = -33;

	/**
	 * 会议室未开始
     */
    int DB_RS_CLOUDMEETING_ROOM_NOTSTARTED = -34;

    /**
     * 包月或季度或包年到期
     */
    int DB_RS_PRODUCT_EXPIRED = -61;

}
