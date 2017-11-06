package com.pioneer.workflow.common;

/**
 * rest接口通用返回对象
 *
 * @author luxinglin
 * @version 1.0.0
 * @since 2017/3/29.
 */
public class RestRetn<T> {
    public static final String DUPLICATE = "DUPLICATE";
    public static final String NO_RESULT = "NO RESULT";
    public static final String NOT_FOUND = "NOT FOUND";

    public static final String SUCCESS = "true";
    protected static final String ERROR = "false";
    private String success;
    private String errorCode;
    private String message;
    private int httpResponseCode = 200;
    private T data;

    public RestRetn() {
        this.success = ERROR;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS.equals(success);
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpResponseCode() {
        return this.httpResponseCode;
    }

    public void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
