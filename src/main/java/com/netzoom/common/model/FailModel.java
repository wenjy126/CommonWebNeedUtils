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

	public FailModel(Object message) {
		super(Constant.FAIL, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}
