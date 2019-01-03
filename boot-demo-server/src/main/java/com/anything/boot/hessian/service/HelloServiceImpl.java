package com.anything.boot.hessian.service;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/23 19:01
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello," + name;
    }
}
