package com.anything.boot.validator.app;

import com.anything.boot.validator.form.LoginForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/20 12:15
 */
@RestController
@RequestMapping("/valid")
public class ValidatorDemoController {

    @GetMapping("/c")
    public void check(@Valid LoginForm loginForm){

        System.out.println(loginForm);
    }
}
