package com.netzoom.common.handler;

import com.netzoom.common.exception.ValidOrderTypesException;
import com.netzoom.common.exception.ValidationException;
import com.netzoom.common.model.BaseModel;
import com.netzoom.common.model.FailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;

/**
 * 全局异常捕捉类
 *
 * @author ：luje
 * @date ：Created in 2019/8/20 17:13
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 捕捉传参校验不通过的异常处理
     *
     * @param e MethodArgumentNotValidException
     * @return BaseModel
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseModel paramValidationException(MethodArgumentNotValidException e) {
        logger.error("参数校验失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        if (error == null) {
            return FailModel.paramsError("参数校验失败");
        }
        String field = error.getField();
        return FailModel.paramsError(field + "参数异常");
    }

    /**
     * 捕获空值异常
     *
     * @param e ValidationException
     * @return BaseModel
     */
    @ExceptionHandler(ValidationException.class)
    public BaseModel validationException(ValidationException e) {
        logger.error("参数为空，{}", e.getMessage());
        return FailModel.paramsError(e.getMessage());
    }

    /**
     * 捕获正则表达式校验不通过的异常：
     *
     * @param e UnexpectedTypeException
     * @return BaseModel
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public BaseModel unexpectedTypeException(UnexpectedTypeException e) {
        logger.error("参数解析失败", e);
        return FailModel.paramsError(e.getMessage() + "参数异常");
    }

    /**
     * 捕获排序参数异常
     *
     * @param e ValidOrderTypesException
     * @return BaseModel
     */
    @ExceptionHandler(ValidOrderTypesException.class)
    public BaseModel validOrderTypesException(ValidOrderTypesException e) {
        logger.error("排序参数异常", e);
        return FailModel.paramsError("排序参数异常");
    }

    /**
     * 捕获数据库异常
     *
     * @param e DataAccessException
     * @return BaseModel
     */
    @ExceptionHandler(DataAccessException.class)
    public BaseModel dataAccessException(DataAccessException e) {
        logger.error("数据库异常", e);
        return FailModel.internalError("数据库异常");
    }


    /**
     * 捕获所有异常
     *
     * @param e Exception
     * @return BaseModel
     */
    @ExceptionHandler(Exception.class)
    public BaseModel exception(Exception e) {
        logger.error("出现异常", e);
        return FailModel.internalError("服务异常");
    }
}
