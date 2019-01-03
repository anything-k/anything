package com.iboot.core.context.annotation;

import java.lang.annotation.*;

/**
 * 分页注解
 *
 * @Author:FanMingxin
 * @Date: 2018-06-04 下午 19:28
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pagination {

}
