package cn.kevin.advice;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentInvalidException(MethodArgumentNotValidException e) {
        return "catch exception:" + e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        return "系统异常";
    }

    @ExceptionHandler(BindException.class)
    public String bindException() {
        return "bindException";
    }
}
