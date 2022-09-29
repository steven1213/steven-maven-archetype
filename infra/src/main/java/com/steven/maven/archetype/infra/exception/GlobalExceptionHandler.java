package com.steven.maven.archetype.infra.exception;

import com.google.common.base.Joiner;
import com.steven.maven.archetype.infra.general.resp.Resp;
import com.steven.maven.archetype.infra.general.types.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: steven
 * @date: 2022/9/29 22:53
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Resp<Void> bizExceptionHandler(BizException ex) {
        log.error("biz exception:", ex);
        return Resp.failure(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Resp<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("methodArgumentNotValidException :", ex);
        List<String> errMsgList = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        String errorMsg = Joiner.on(",").join(errMsgList);
        return Resp.failure(ResultCode.FAILURE.getKey(), errorMsg);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Resp<Void> nullPointerExceptionHandler(NullPointerException ex) {
        log.error("NPE :", ex);
        return Resp.failure(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Resp<Void> exceptionHandler(Exception ex) {
        log.error("exception :", ex);
        return Resp.failure(ResultCode.FAILURE);
    }
}
