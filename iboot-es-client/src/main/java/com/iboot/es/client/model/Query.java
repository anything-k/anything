package com.iboot.es.client.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iboot.es.client.enums.QueryFieldEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 11:19
 */
public abstract class Query{

    private static final String BOOL_NODE = QueryFieldEnum.BOOL.key();

    private final QueryObject must = new QueryObject(QueryFieldEnum.MUST.key());
    private final QueryObject mustNot = new QueryObject(QueryFieldEnum.MUST_NOT.key());
    private final QueryObject should = new QueryObject(QueryFieldEnum.SHOULD.key());
    private QueryObject q;

    private final QueryPlus query = new QueryPlus();

    public QueryObject build(){
        JSONObject bool = bool();

        QueryObject query = new QueryObject(BOOL_NODE);
        if(!bool.isEmpty()){
            query.putVal(bool);
            return query;
        }else{
            return this.q;
        }
    }

    public JSONObject bool(){
        JSONObject bool = new JSONObject();

        List<QueryObject> boolList = getBool();
        if(!boolList.isEmpty()){
            boolList.forEach(qb->{
                if(!qb.isEmpty()){
                    bool.put(qb.name(),qb.val4Obj());
                }
            });
        }

        return bool;
    }

    public void must(Map<String,Object> map){
        JSONArray matchItems = query.createItem(node(), map);
        must.putVal(matchItems);
    }

    public void mustNot(Map<String,Object> map){
        JSONArray matchItems = query.createItem(node(), map);
        mustNot.putVal(matchItems);
    }

    public void should(Map<String,Object> map){
        JSONArray matchItems = query.createItem(node(), map);
        should.putVal(matchItems);
    }

    public <T> void q(String field,T val){
        this.q = query.createItem(node(),field, val);
    }

    private List<QueryObject> getBool(){
        List<QueryObject> bool = new ArrayList<>();
        bool.add(must);
        bool.add(mustNot);
        bool.add(should);
        return bool;
    }

    public abstract String node();
}
