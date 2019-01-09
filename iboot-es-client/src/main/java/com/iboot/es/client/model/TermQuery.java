package com.iboot.es.client.model;

import com.iboot.es.client.enums.QueryFieldEnum;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 18:32
 */
public class TermQuery extends Query{

    public TermQuery(){

    }

    public void mustIn(){

    }

    @Override
    public String node() {
        return QueryFieldEnum.TERM.key();
    }
}
