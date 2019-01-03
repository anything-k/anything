package com.anything.boot.hessian.app;

import com.anything.boot.hessian.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/23 19:14
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public Object hello(String name){
        return helloService.hello(name);
    }
}
