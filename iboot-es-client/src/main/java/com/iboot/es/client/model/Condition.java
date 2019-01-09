package com.iboot.es.client.model;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/3 18:02
 */
public interface Condition{

    <T> Condition query(String field,T val);

    Condition must(Map<String,Object> map);

    Condition mustNot(Map<String,Object> map);

    Condition should(Map<String,Object> map);

    Condition source(List<String> fields);

    Condition source(String fields);

    Condition limit(Integer from,Integer size);

    Condition sort(Map<String,Boolean> field);

    Condition sort(String fields);

    Condition sort(String fields, boolean asc);

    Condition distinct(String field);

    String toJson();
}
