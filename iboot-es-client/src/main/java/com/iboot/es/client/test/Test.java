package com.iboot.es.client.test;

import com.iboot.es.client.model.BaseCondition;
import com.iboot.es.client.model.Condition;
import com.iboot.es.client.model.MatchQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 22:48
 */
public class Test {

    public static void main(String[] args){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","anything");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("userid",11);
        map2.put("age",22);


        Condition condition = BaseCondition.create(new MatchQuery())
                .query("id",1)
                .must(map)
                .must(map2)
                .source("id,name,age")
                .sort("id",false)
                .sort("name")
                .limit(1,20)
                .distinct("id");

        System.out.println(condition.toJson());
    }
}
