package com.iboot.es.client.enums;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 11:22
 */
public enum QueryFieldEnum {

    MATCH("match",""),
    MATCH_All("match_all",""),
    MULTI_MATCH("multi_match",""),

    TERM("term",""),
    TERMS("terms",""),
    RANGE("range",""),

    MUST("must",""),
    MUST_NOT("must_not",""),
    SHOULD("should",""),
    BOOL("bool",""),

    QUERY("query",""),
    SOURCE("_source",""),
    SORT("sort",""),

    FROM("from",""),
    SIZE("size",""),

    COLLAPSE("collapse",""),

    ;

    private String key;

    private String value;

    QueryFieldEnum(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String key(){
        return key;
    }
}
