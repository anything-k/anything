package com.anything.boot.gateway.app;

import com.example.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Author:FanMingxin
 * @Date: 2018/10/10 14:51
 */
@Api(value = "示例",tags = {"示例1"})
@RestController
@RequestMapping("/demo")
public class DemoTtController {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "测试m",notes = "测试接口")
    @GetMapping("/m")
    public void m(){
        demoService.mono();
    }

    @ApiOperation(value = "测试f",notes = "测试接口")
    @GetMapping("/f")
    public void f(){
        demoService.flux();
    }

    @GetMapping("/timeout")
    public void timeout(){
        try {
            Thread.sleep(1000 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/error")
    public void error(){
        String a = null;
        System.out.println(a.toString());
    }
}
