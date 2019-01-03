package com.anything.boot.feign.service;

import com.anything.boot.feign.entity.User;
import com.anything.boot.feign.hystrix.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/10 18:11
 */
@FeignClient(name = "service-producer",fallback = UserServiceFallBack.class)
public interface IUserService {

    @RequestMapping(value = "/user/{id}")
    User getUser(@PathVariable("id") Integer id);
}
