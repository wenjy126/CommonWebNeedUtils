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

	public FailModel() {
	}

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
	private FailModel(Object message,Integer errorCode) {
		super(Constant.FAIL, message, errorCode);
	}

	/**
	 * 逻辑错误返回
	 * @param message 返回信息
	 * @return BaseModel
	 */
	public static FailModel internalError(String message){
		return new FailModel(message,Constant.INTERNAL_ERROR);
	}

	/**
	 * 参数错误返回
	 * @param message 返回信息
	 * @return BaseModel
	 */
	public static FailModel paramsError(String message){
		return new FailModel(message,Constant.PARAMS_ERROR);
	}

	/**
	 * 普通错误构造
	 * @param message 返回信息
	 * @param errorCode 错误代码
	 * @return BaseModel
	 */
	public static FailModel failModel(String message,Integer errorCode){
		return new FailModel(message,errorCode);
	}

}
