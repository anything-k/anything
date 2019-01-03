package com.anything.boot.validator.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/21 9:21
 */
@Data
public class LoginForm {

    @NotNull(message = "{loginForm.user.notBlank}")
    @Size(min=2, max=30,message = "姓名长度必须是2到30之间")
    private String name;

    private Integer age;
}
