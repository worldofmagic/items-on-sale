package com.rbc.itemsonsale.model;

import lombok.Data;

@Data
public class Response<T> {

    /**
     * status code
     */
    private Integer code;

    /**
     * response object
     */
    private T data;

    /**
     * tip message
     */
    private String msg;

}

