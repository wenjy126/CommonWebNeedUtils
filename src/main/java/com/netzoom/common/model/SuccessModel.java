package com.netzoom.common.model;



import org.springframework.http.HttpStatus;
import com.netzoom.common.util.Constant;

/**
 * 成功消息传递模型
 * @author tanzj
 */
public class SuccessModel extends BaseModel {

	public SuccessModel() {
	}

	/**
	 * 传统成功模型构造器
	 *
	 * @param message 成功信息
	 */
	public SuccessModel(Object message) {
		super(Constant.SUCCESS, message, HttpStatus.OK.value());
	}

	/**
	 * 成功模型构造器
	 * @param message 成功信息
	 * @param data 传出的data
	 */
	public SuccessModel(Object message, Object data) {
		super(data,Constant.SUCCESS, message, Constant.SUCCESS_CODE);
	}
}
