package com.iboot.core.context.annotation;

import java.lang.annotation.*;

/**
 * rest返回值注解
 *
 * @Author:FanMingxin
 * @Date: 2018-05-09 下午 02:28
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestStatus {

    /**
     * 接口返回值message中值
     *
     * @return
     */
    String value() default "ok";
}
