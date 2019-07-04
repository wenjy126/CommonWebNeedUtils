package com.netzoom.common.model;



import org.springframework.http.HttpStatus;
import com.netzoom.common.util.Constant;

/**
 * 成功消息传递模型
 * @author tanzj
 */
public class SuccessModel extends BaseModel {

	public SuccessModel(Object message) {
		super(Constant.SUCCESS, message, HttpStatus.OK.value());
	}
}
