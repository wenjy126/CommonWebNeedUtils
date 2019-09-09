package com.netzoom.common.util;

import com.netzoom.common.annotation.FieldName;
import com.netzoom.common.exception.ValidationException;
import com.netzoom.common.model.BaseModel;
import com.netzoom.common.model.FailModel;
import com.netzoom.common.model.SuccessModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用校验器
 *
 * @author tanzj
 */
public class ValidationUtil {

    private final static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 手机号规则
     */
    private static final Pattern MOBILE_PATTERN =  Pattern.compile("^((861)|(1))\\d{10}$");

    /**
     * 邮箱规则
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
    /**
     * 正则校验
     *
     * @param expressionString 规则
     * @param info             需要匹配的信息
     * @return boolean 是否匹配
     */
    public static boolean checkRegExpression(String expressionString, String info) {
        Pattern pattern = Pattern.compile(expressionString);
        Matcher matcher = pattern.matcher(info);
        return matcher.matches();
    }

    /**
     * 字符串通配方法,只能匹配/api/* 类型
     * @param patternString 通配符 如/api/*
     * @param content 需要匹配的内容 如/api/test
     * @return boolean
     */
    public static boolean StringMatcher(String patternString,String content){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(content);
        return matcher.lookingAt();
    }

    /**
     * 验证手机号码是否符合基本格式
     * @param mobile 待验证的手机号
     * @return boolean
     */
    public static boolean checkMobileNo(String mobile){
        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 验证邮箱是否符合格式
     * @param email 待校验的邮箱
     * @return boolean
     */
    public static boolean checkEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }


    /**
     * 对象空值校验器
     * @param targetObject 传入的目标对象
     * @param params 需要校验的参数
     * @return BaseModel success/fail
     */
    public static BaseModel validateParamsBlankAndNull(Object targetObject, String... params){
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
                try {
                    method = clazz.getSuperclass().getMethod("get"+param.substring(0,1).toUpperCase()+param.substring(1));
                } catch (NoSuchMethodException e1) {
                    return FailModel.paramsError("参数"+param+"不存在");
                }
            }
            Object result = null;
            try {
                //取得getter执行结果
                result =  method.invoke(targetObject);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
                return FailModel.internalError("方法不可执行");
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
                return FailModel.internalError("传入对象异常");
            }
            if (result==null || "".equals(result)){
                FieldName fieldName = null;
                try {
                    fieldName = clazz.getDeclaredField(param).getAnnotation(FieldName.class);
                }catch (NoSuchFieldException e){
                    try {
                        fieldName = clazz.getSuperclass().getDeclaredField(param).getAnnotation(FieldName.class);
                    } catch (NoSuchFieldException e1) {
                        return FailModel.paramsError("参数"+param+"不存在");
                    }
                }
                if (fieldName!=null){
                    stringBuilder.append(fieldName.value()+"不能为空，");
                }else {
                    stringBuilder.append(param+"不能为空;");
                }
                errorNumber++;
            }
        }
        if (errorNumber==0){
            return SuccessModel.withoutData("校验通过");
        }else {
            throw new ValidationException(stringBuilder.toString());
        }

    }
}
