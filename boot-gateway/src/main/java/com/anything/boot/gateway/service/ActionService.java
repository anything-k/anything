package com.anything.boot.gateway.service;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/10/30 10:50
 */
@Service
public class ActionService {

    public void action(String str){
        System.out.println(str);
        System.out.println(Thread.currentThread().getName());
    }
}
