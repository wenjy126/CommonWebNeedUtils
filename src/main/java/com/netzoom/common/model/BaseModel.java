package com.netzoom.common.model;


import com.netzoom.common.annotation.FieldName;

/**
 * 组件间消息传输载体
 *
 * @author TanzJ
 * @CreateTime 2018/12/26 21:54
 */
public class BaseModel {


	@FieldName(value = "结果",comment = "操作结果-success/fail")
	private String result;

	@FieldName(value = "返回信息",comment = "这里写备注")
	private Object message;

	@FieldName(value = "状态码")
	private Integer code;

	public BaseModel(String result, Object message, Integer code) {
		this.result = result;
		this.message = message;
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public BaseModel() {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public BaseModel(String result, Object message) {
		this.result = result;
		this.message = message;
	}


}
