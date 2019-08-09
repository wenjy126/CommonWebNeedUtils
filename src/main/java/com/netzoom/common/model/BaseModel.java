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

	@FieldName("业务返回数据")
	private Object data;

	public BaseModel(String result, Object message, Integer code) {
		this.result = result;
		this.message = message;
		this.code = code;
	}

	public BaseModel(Object data,String result, Object message, Integer code) {
		this.result = result;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public BaseModel setCode(Integer code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public BaseModel setData(Object data) {
		this.data = data;
		return this;
	}

	public BaseModel() {
	}

	public String getResult() {
		return result;
	}

	public BaseModel setResult(String result) {
		this.result = result;
		return this;
	}

	public Object getMessage() {
		return message;
	}

	public BaseModel setMessage(Object message) {
		this.message = message;
		return this;
	}

	public BaseModel(String result, Object message) {
		this.result = result;
		this.message = message;
	}


}
