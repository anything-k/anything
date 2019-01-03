package com.anything.boot.feign.app;

import com.anything.boot.feign.entity.UserData;
import com.iboot.core.context.annotation.RestCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/10 18:15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RestCode
    @RequestMapping("/{id}")
    public Object detail(@PathVariable Integer id){

        return new UserData(id,"feign",11);
    }
}
