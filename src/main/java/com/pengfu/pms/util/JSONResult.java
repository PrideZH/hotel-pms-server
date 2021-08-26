package com.pengfu.pms.util;

import com.pengfu.pms.entity.Room;

/**
 * @author PrideZH
 * @date 2021/7/19 - 20:33
 */
public class JSONResult {

    /**
     * 响应状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    private JSONResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JSONResult build(Integer code, String message, Object data) {
        return new JSONResult(code, message, data);
    }

    public static JSONResult build(ResultCode resultCode, Object data) {
        return new JSONResult(resultCode.code(), resultCode.message(), data);
    }

    public static JSONResult errorMessage(String message) {
        return new JSONResult(ResultCode.ERROR.code(), message,null);
    }

    public static JSONResult ok(Object data) {
        return build(ResultCode.SUCCESS, data);
    }

    public static JSONResult error(Object data) {
        return build(ResultCode.ERROR, data);
    }

    public static JSONResult userNotExist() {
        return build(ResultCode.USER_NOT_EXIST, null);
    }

    public static JSONResult userNameExist() {
        return build(ResultCode.USER_NAME_EXIST, null);
    }

    @Override
    public String toString() {
        return "{\"code\": " + code + ", \"message\": \"" + message + "\", \"data\": " + data + "}";
    }
}
