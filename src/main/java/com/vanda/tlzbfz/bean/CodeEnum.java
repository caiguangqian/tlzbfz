package com.vanda.tlzbfz.bean;

public enum CodeEnum {
    SUCCESS("200","成功"), UNAUTH("400","失败"), EXTOKEN("401","token过期");
    private String code;
    private String msg;


    private CodeEnum(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    public String getCm() {
        return code+"--"+msg;
    }

}
