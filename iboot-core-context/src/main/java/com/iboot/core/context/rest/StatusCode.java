package com.iboot.core.context.rest;

/**
 * 通用状态码
 *
 * @author FanMingxin
 * @date 2018/10/22 11:39
 */
public enum StatusCode {

    /**
     * 业务成功
     */
    SUCCESS(0, "成功"),

    /**
     * 业务失败
     */
    FAIL(-1, "失败"),

    /**
     * 业务验证不通过
     */
    VALIDATE_FAIL(-2,"业务验证不通过");

    private int key;
    private String value;

    StatusCode(final int key, final String value) {
        this.key = key;
        this.value = value;
    }

    public int key() {
        return key;
    }

    public String value() {
        return value;
    }
}
