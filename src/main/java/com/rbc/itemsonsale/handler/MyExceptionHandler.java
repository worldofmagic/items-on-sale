package com.rbc.itemsonsale.handler;

import com.rbc.itemsonsale.exception.MyBasicException;
import com.rbc.itemsonsale.model.Response;
import com.rbc.itemsonsale.util.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyBasicException.class)
    @ResponseBody
    public Response handler(MyBasicException se) {
        return ResponseUtils.error(se.getCode(), se.getMessage());
    }
}
