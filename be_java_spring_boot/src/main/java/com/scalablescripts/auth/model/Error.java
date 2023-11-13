package com.scalablescripts.auth.model;

public class Error {
    private int id;
    private String errCode;
    private String errMessage;

    public Error(int id, String errCode, String errMessage) {
        this.id = id;
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
