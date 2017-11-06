package com.pioneer.workflow.common;

public class UserException extends RuntimeException {

    private static final long serialVersionUID = 4170213429597095681L;
    private String errCode;

    public UserException() {
        // TODO 自动生成的构造函数存根
    }

    public UserException(String errCode, String message) {
        super(message);
        // TODO 自动生成的构造函数存根
        this.errCode = errCode;
    }

    public UserException(String message) {
        super(message);
        // TODO 自动生成的构造函数存根
    }

    public UserException(Throwable cause) {
        super(cause);
        // TODO 自动生成的构造函数存根
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
        // TODO 自动生成的构造函数存根
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}