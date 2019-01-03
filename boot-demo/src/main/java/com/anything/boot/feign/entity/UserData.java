package com.anything.boot.feign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/10 18:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    private Integer id;

    private String name;

    private Integer age;
}
