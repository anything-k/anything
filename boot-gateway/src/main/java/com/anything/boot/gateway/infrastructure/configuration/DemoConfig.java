package com.anything.boot.gateway.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/10/30 15:36
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "demo")
public class DemoConfig {

    private String name;
    private String birthYear;
    private List<String> list;
    private Set<Integer> set;
    private Map<String,Object> map;
}
