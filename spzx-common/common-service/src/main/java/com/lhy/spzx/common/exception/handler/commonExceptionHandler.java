package com.lhy.spzx.common.exception.handler;


import com.lhy.spzx.common.exception.BusinessException;
import com.lhy.spzx.model.vo.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class commonExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        return Result.build(null,e.getResCode());
    }
}
