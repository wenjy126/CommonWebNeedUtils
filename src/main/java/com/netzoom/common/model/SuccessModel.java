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
	private SuccessModel(Object message) {
		super(Constant.SUCCESS, message, HttpStatus.OK.value());
	}

	/**
	 * 成功模型构造器
	 * @param message 成功信息
	 * @param data 传出的data
	 */
	private SuccessModel(Object message, Object data) {
		super(data,Constant.SUCCESS, message, Constant.SUCCESS_CODE);
	}

	/**
	 * 带Code构造器
	 * @param message 成功信息
	 * @param data 成功数据
	 * @param code 成功状态码
	 */
	private SuccessModel(Object message,Object data,Integer code){
		super(data,Constant.SUCCESS,message,code);
	}

	/**
	 * 成功模型构造器
	 * @param message 返回信息
	 * @param data 数据
	 * @return SuccessModel
	 */
	public static SuccessModel successModel(Object message,Object data){
		return new SuccessModel(message,data);
	}

	/**
	 * 带成功代码模型构造器
	 * @param message 成功信息
	 * @param data 成功数据
	 * @param code 成功代码
	 * @return SuccessModel
	 */
	public static SuccessModel successModelWithCode(Object message,Object data,Integer code){
		return new SuccessModel(message,data,code);
	}
}
