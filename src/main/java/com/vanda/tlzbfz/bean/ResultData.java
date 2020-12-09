package com.vanda.tlzbfz.bean;

import com.vanda.tlzbfz.common.util.DateUtils;

public class ResultData<T> {
    private String status;
    private String message;
    private String date;
    private T data;

    public ResultData(){}

    public ResultData(String status, T data) {
        if(status.indexOf("--")!=-1){
            String[] sm=status.split("--");
            this.status = sm[0];
            this.message = sm[1];
        }else{
            this.status = status;
            this.message = message;
        }
        this.date = DateUtils.getDateTime();
        this.data = data;

    }

    public ResultData(String status, String message, T data) {
        if(status.indexOf("--")!=-1){
            String[] sm=status.split("--");
            this.status = sm[0];
            this.message = sm[1];
        }else{
            this.status = status;
            this.message = message;
        }
        this.date = DateUtils.getDateTime();
        this.data = data;

    }

    public void setStatus2(String status) {
        if(status.indexOf("--")!=-1){
            String[] sm=status.split("--");
            this.status = sm[0];
            this.message = sm[1];
        }else{
            this.status = status;
        }
        this.date = DateUtils.getDateTime();
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", data='" + data + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

