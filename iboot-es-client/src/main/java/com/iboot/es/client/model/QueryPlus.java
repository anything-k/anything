package com.iboot.es.client.model;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 11:46
 */
public class QueryPlus {


    public JSONArray createItem(String itemName,Map<String,Object> map){
        if(map == null || map.isEmpty()){
            return null;
        }


        JSONArray item = new JSONArray();
        map.forEach((key,val)->
                item.add(new QueryObject(itemName,key,val).toJson())
        );

        return item;
    }

    public <T> QueryObject createItem(String itemName, String field, T val){

        return new QueryObject(itemName,field,val);
    }
}
