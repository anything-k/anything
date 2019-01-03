package com.anything.boot.feign.app;

import com.anything.boot.feign.service.IUserService;
import com.iboot.core.context.annotation.RestCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/11 9:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RestCode
    @GetMapping("/{id}")
    public Object detail(@PathVariable Integer id){

        return iUserService.getUser(id);
    }
}
