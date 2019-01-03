package com.iboot.core.context.rest;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/5 15:20
 */
public enum RestFieldEnum{

    /**
     *
     */
    CODE("code","响应码"),

    STATUS("status","响应码"),

    MESSAGE("message","响应信息"),

    ERROR_MESSAGE("err_msg","响应信息"),

    TOTAL("total","总数"),

    DATA("data","业务数据");


    private String key;

    private String value;

    RestFieldEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String key(){
        return this.key;
    }

    public String value(){
        return this.value;
    }
}
