package com.anything.boot.gateway.service;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @Author:FanMingxin
 * @Date: 2018/10/10 20:01
 */
@Service
public class ActionService {

    public void action(String str){
        System.out.println(str);
        System.out.println(Thread.currentThread().getName());
    }
}
