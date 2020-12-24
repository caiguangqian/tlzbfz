package com.vanda.tlzbfz.common.util;

import lombok.Data;

@Data
public class ResultMsg<T> {

    private String code;
    private String message;
    private T data;


    public ResultMsg(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultMsg() {

    }
}
