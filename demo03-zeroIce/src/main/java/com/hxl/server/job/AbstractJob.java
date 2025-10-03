package com.hxl.server.job;


import com.hxl.server.constants.Constants;

import java.util.Date;

/**
 *
* @ClassName: AbstractJob
* @Description: 工作抽象类
* @author Scott
*
 */
public abstract class AbstractJob {

	/**
	 * 请求接收时间
	 */
	private final Date receiveTime = new Date();

	public int result = Constants.SUCCESS;

	public int getResult() {

		return result;

	}


	/**
	 *
	* @Title: getResult
	* @Description: 判断结果码，resultCode为数据库查询结果，resultCode=Constants.EMPTY_RESULT为数据库查询为空值
	*               1.优先返回异常结果
	*               2.数据查询为空
	*               3.查询正常
	* @param @param resultCode 数据库查询结果
	* @param @return
	* @return int
	 */
	public int getResult(int resultCode){

		if(result == Constants.EXCEPTION){

			return result;

		}

		if(resultCode == Constants.EMPTY_RESULT){

			return Constants.EMPTY_RESULT;

		}

		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}

	/**
	 *
	* @Title: execute
	* @Description: 工作线程的执行方法
	* @param
	* @return void
	 */
	public abstract void execute();

	public Date getReceiveTime() {
		return receiveTime;
	}
}
