package com.iboot.es.client.util;

import com.alibaba.fastjson.JSONObject;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 14:03
 */
public class JSONBuilder {

    public static <T> JSONObject build(String key,T value){
        JSONObject matchItem = new JSONObject();
        matchItem.put(key,value);
        return matchItem;
    }
}
