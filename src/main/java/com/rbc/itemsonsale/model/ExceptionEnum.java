package com.rbc.itemsonsale.model;

public enum ExceptionEnum {
    UNKNOWN_ERROR(-100, "unknow error"),
    NEED_LOGIN(403, "Forbidden"),
    NOT_FOUND(404,"Not Found"),
    USER_NOT_FOUND(404, "User Not Found"),
    SYSTEM_ERROR(500,"System Error");

    private Integer code;

    private String msg;

    private ExceptionEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {

        return code;
    }

    public String getMsg() {

        return msg;
    }
}
