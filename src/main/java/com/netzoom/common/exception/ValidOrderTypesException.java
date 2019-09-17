package com.netzoom.common.exception;

/**
 * 校验分页排序字段非有效性异常
 * @author ：luje
 * @date ：Created in 2019/9/12 13:49
 */
public class ValidOrderTypesException extends RuntimeException {

    public ValidOrderTypesException(String message) {
        super(message);
    }
}
