package com.rbc.itemsonsale.exception;

import com.rbc.itemsonsale.model.ExceptionEnum;
import lombok.Getter;

@Getter
public class MyBasicException extends RuntimeException{
    private Integer code;

    /**
     * Using existing exceptions in enum
     * @param type
     */
    public MyBasicException(ExceptionEnum type){
        super(type.getMsg());
        this.code = type.getCode();
    }

    /**
     * for unknow exception
     * @param code
     * @param msg
     */
    public MyBasicException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}

