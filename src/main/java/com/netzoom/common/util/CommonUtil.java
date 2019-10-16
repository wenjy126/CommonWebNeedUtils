package com.netzoom.common.util;

import com.alibaba.fastjson.JSON;
import com.netzoom.common.interfaces.FieldMethod;
import com.netzoom.common.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 通用工具类
 *
 * @author tanzj
 */
public class CommonUtil {

	/**
	 * 构造不带“-”的UUID
	 * @return String
	 */
	@Deprecated
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private final static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * 将符合格式的jsonString转化成BaseModel
	 *
	 * @param jsonString 符合BaseModel格式的jsonString
	 * @return BaseModel
	 */
	public static BaseModel covertToBaseModel(String jsonString) {
		return JSON.toJavaObject(JSON.parseObject(jsonString), BaseModel.class);
	}

	/**
	 * 用slf4j-info级别的logger将对象以JSON的形式打印
	 *
	 * @param object 传入的对象
	 */
	public static void makeInfoLog(Object object) {
		logger.info("打印结果：" + JSON.toJSONString(object));
	}

	/**
	 * 对象空值校验器
	 * @param targetObject 传入的目标对象
	 * @param params 需要校验的参数
	 * @return BaseModel success/fail
	 */
	public static BaseModel validateParamsBlankAndNull(Object targetObject,String... params){
		return ValidationUtil.validateParamsBlankAndNull(targetObject,params);
	}

	/**
	 * 对象空值校验器
	 *
	 * @param fieldMethods 传入方法引用
	 * @return BaseModel success/fail
	 */
	public static BaseModel validateParamsBlankAndNull(FieldMethod... fieldMethods) {
		return ValidationUtil.validateParamsBlankAndNull(fieldMethods);
	}

}
