package com.iboot.es.client.model;

import com.iboot.es.client.enums.QueryFieldEnum;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2019/1/7 18:32
 */
public class MatchQuery extends Query{

    public MatchQuery(){

    }

    @Override
    public String node() {
        return QueryFieldEnum.MATCH.key();
    }
}
