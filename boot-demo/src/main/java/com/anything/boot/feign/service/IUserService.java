package com.anything.boot.feign.service;

import com.anything.boot.feign.entity.UserData;
import com.anything.boot.feign.hystrix.HystrixClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/10 18:11
 */
@FeignClient(name = "user",url = "http://localhost:9000",fallbackFactory = HystrixClientFallbackFactory.class)
public interface IUserService {

    @RequestMapping(value = "/user/{id}",headers = {"return:all"})
        //@Headers("return: all")
    //@RequestLine("GET /user/{id}")
    UserData getUser(@PathVariable("id") Integer id);
}
