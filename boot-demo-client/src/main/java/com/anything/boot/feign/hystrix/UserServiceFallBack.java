package com.anything.boot.feign.hystrix;

import com.anything.boot.feign.entity.User;
import com.anything.boot.feign.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/19 8:56
 */
@Component
public class UserServiceFallBack implements IUserService {
    @Override
    public User getUser(Integer id) {
        return new User(0,"降级2",1);
    }
}
