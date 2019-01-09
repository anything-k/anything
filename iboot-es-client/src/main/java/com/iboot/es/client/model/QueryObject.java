package com.iboot.es.client.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iboot.es.client.enums.QueryFieldEnum;
import com.iboot.es.client.util.JSONBuilder;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 14:05
 */
public class QueryObject {

    private String name;

    private JSONArray val;

    public QueryObject(String name) {
        this.name = name;
        this.val = new JSONArray();
    }

    public QueryObject(String name, JSONArray query) {
        this(name);
        this.val = query;
    }

    public <T> QueryObject(String name,String key,T value){
        this(name);
        this.val.add(JSONBuilder.build(key,value));
    }

    public JSONObject toJson(){
        if(this.val == null){
            return null;
        }

        if(this.val.size() == 1){
            return JSONBuilder.build(this.name, this.val.getJSONObject(0));
        }
        return JSONBuilder.build(this.name, this.val);
    }

    public Object val4Obj(){
        if(this.val == null){
            return null;
        }

        if(this.val.size() == 1){
            return this.val.getJSONObject(0);
        }
        return this.val;
    }

    public JSONArray val(){
        return this.val;
    }

    public String name(){
        return this.name;
    }

    public boolean eq(QueryFieldEnum queryFieldEnum){
        return queryFieldEnum.key().equals(this.name);
    }

    public boolean isEmpty(){
        return this.val == null || this.val.isEmpty();
    }

    public void putVal(JSONArray value){
        if(value == null || value.isEmpty()){
            return;
        }

        if(this.val == null){
            this.val = value;
        }else{
            this.val.addAll(value);
        }
    }

    public void putVal(JSONObject value){
        if(value == null || value.isEmpty()){
            return;
        }

        if(this.val == null){
            this.val = new JSONArray();
        }

        this.val.add(value);
    }
}
