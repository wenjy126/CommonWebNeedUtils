package com.netzoom.common.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.netzoom.common.util.Constant;

/**
 * 失败消息传输模型
 * @author tanzj
 */
public class FailModel extends BaseModel {

	/**
	 * 错误模型构造器,使用500作为错误码
	 * @param message 传出的参数
	 */
	@Deprecated
	public FailModel(Object message) {
		super(Constant.FAIL, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	/**
	 * 错误模型构造器
	 * @param message 传出的参数
	 * @param errorCode 传出的业务错误码
	 */
	public FailModel(Object message,Integer errorCode) {
		super(Constant.FAIL, message, errorCode);
	}

}
