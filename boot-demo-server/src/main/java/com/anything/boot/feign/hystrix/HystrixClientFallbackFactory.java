package com.anything.boot.feign.hystrix;

import com.anything.boot.feign.entity.UserData;
import com.anything.boot.feign.service.IUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/12 17:21
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<IUserService> {
    @Override
    public IUserService create(Throwable throwable) {
        return (id -> new UserData(0,"降级",1));
    }
}
