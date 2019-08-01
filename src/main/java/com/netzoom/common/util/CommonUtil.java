package com.netzoom.common.util;

import com.alibaba.fastjson.JSON;
import com.netzoom.common.annotation.FieldName;
import com.netzoom.common.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

//	private final static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

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
		System.out.println("打印结果：" + JSON.toJSONString(object));
	}

	/**
	 * 对象空值校验器
	 * @param targetObject 传入的目标对象
	 * @param params 需要校验的参数
	 * @return BaseModel success/fail
	 */
	public static BaseModel validateParamsBlankAndNull(Object targetObject,String... params) throws NoSuchFieldException {
		Class clazz = targetObject.getClass();
		Integer errorNumber = 0;
		StringBuilder stringBuilder = new StringBuilder();
		for (String param:params){
			param = param.trim();
			Method method = null;
			try {
				//将param首字母大写
				method = clazz.getMethod("get"+param.substring(0,1).toUpperCase()+param.substring(1));
			} catch (NoSuchMethodException e) {
				return new BaseModel(Constant.FAIL,"参数"+param+"不存在");
			}
			String result = null;
			try {
				//取得getter执行结果
				result = (String) method.invoke(targetObject);
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
				return new BaseModel(Constant.FAIL,"方法不可执行");
			} catch (InvocationTargetException e) {
				System.out.println(e.getMessage());
				return new BaseModel(Constant.FAIL,"传入对象异常");
			}
			if (result==null || "".equals(result)){
				FieldName fieldName = clazz.getDeclaredField(param).getAnnotation(FieldName.class);
				if (fieldName!=null){
					stringBuilder.append(fieldName.value()+"不能为空，");
				}else {
					stringBuilder.append(param+"不能为空，");
				}
				errorNumber++;
			}
		}
		return errorNumber==0 ? new BaseModel(Constant.SUCCESS,"校验通过"):new BaseModel(Constant.FAIL,stringBuilder);
	}



}
