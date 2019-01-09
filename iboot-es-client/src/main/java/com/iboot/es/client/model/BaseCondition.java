package com.iboot.es.client.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iboot.es.client.enums.QueryFieldEnum;
import com.iboot.es.client.util.JSONBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/8 16:54
 */
public class BaseCondition implements Condition{

    private final JSONObject condition = new JSONObject();
    private List<String> fields = new ArrayList<>();
    private Integer from = 0;
    private Integer size = 10;
    private JSONArray sort = new JSONArray();

    private Query query;

    public BaseCondition(Query query){
        this.query = query;
    }

    public static BaseCondition create(Query query){
        return new BaseCondition(query);
    }

    @Override
    public <T> Condition query(String field,T val) {
        this.query.q(field,val);
        return this;
    }

    @Override
    public Condition must(Map<String, Object> map) {
        this.query.must(map);
        return this;
    }

    @Override
    public Condition mustNot(Map<String, Object> map) {
        this.query.mustNot(map);
        return this;
    }

    @Override
    public Condition should(Map<String, Object> map) {
        this.query.should(map);
        return this;
    }

    @Override
    public Condition source(List<String> fields) {
        this.fields.addAll(fields);
        return this;
    }

    @Override
    public Condition source(String fields) {
        this.fields.addAll(Arrays.asList(fields.split(",")));
        return this;
    }

    @Override
    public Condition limit(Integer from, Integer size) {
        this.from = from;
        this.size = size;
        return this;
    }

    @Override
    public Condition sort(Map<String, Boolean> field) {
        field.forEach((key,val)->{
            JSONObject value = JSONBuilder.build("order", val ? "asc" : "desc");
            sort.add(JSONBuilder.build(key,value));
        });
        return this;
    }

    @Override
    public Condition sort(String fields) {
        return sort(fields,true);
    }

    @Override
    public Condition sort(String fields,boolean asc) {
        Arrays.asList(fields.split(",")).forEach(key->{
            JSONObject value = JSONBuilder.build("order", asc ? "asc" : "desc");
            sort.add(JSONBuilder.build(key,value));
        });
        return this;
    }

    @Override
    public Condition distinct(String field) {
        condition.put(QueryFieldEnum.COLLAPSE.key(), JSONBuilder.build("field",field));
        return this;
    }

    @Override
    public String toJson() {
        condition.put(QueryFieldEnum.QUERY.key(),query.build().toJson());
        condition.put(QueryFieldEnum.SOURCE.key(),fields);
        condition.put(QueryFieldEnum.FROM.key(),from);
        condition.put(QueryFieldEnum.SIZE.key(),size);
        condition.put(QueryFieldEnum.SORT.key(),sort);
        return condition.toJSONString();
    }
}
