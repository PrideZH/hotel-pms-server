package com.pengfu.pms.util;

/**
 * @author PrideZH
 * @date 2021/7/22 - 18:08
 */
public enum ResultCode {

    /* 状态码 */
    SUCCESS(200, "成功"),
    ERROR(400 , "错误"),
    /* 参数错误 */
    PARAM_IS_INVALID(1001, "参数无效"),
    /* 用户错误 */
    USER_NOT_LOGIN(2000, "登录已过期"),
    USER_NOT_EXIST(2001, "用户不存在"),
    USER_NAME_EXIST(2002, "用户名存在");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
