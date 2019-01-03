package com.anything.boot.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/11 10:12
 */
public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(requestTemplate);
    }
}
