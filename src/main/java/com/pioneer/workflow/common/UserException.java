package com.pioneer.workflow.common;

/**
 * 用户自定义的业务以上类
 *
 * @author luxinglin
 * @version 1.0.0
 * @since 2017/11/03.
 */
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 4170213429597095681L;
    private String errCode;

    public UserException() {
    }

    public UserException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}