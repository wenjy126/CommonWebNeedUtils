package com.netzoom.common.util;

import com.netzoom.common.annotation.FieldName;

import java.lang.reflect.Field;

/**
 * @author tanzj
 * fieldName字段解析器
 */
public class FieldNameHandler {

	public Object resolveFieldName(Object object){
		for (Field field :object.getClass().getDeclaredFields()){
			FieldName fieldName = field.getAnnotation(FieldName.class);
			if (fieldName !=null){
				System.out.println("字段["+field.getName()+"]中文释义为："+ fieldName.value());
			}
		}
		return object;
	}


}
