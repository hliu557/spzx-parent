package com.lhy.spzx.common.exception;

import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private ResultCodeEnum resCode;
    private int code;
    private String message;
    public BusinessException(ResultCodeEnum resCode){
        this.resCode = resCode;
        this.code = resCode.getCode();
        this.message = resCode.getMessage();

    }
}
