package com.anything.boot;

import com.anything.boot.hessian.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/10/30 10:50
 */
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.anything","com.iboot",})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private HelloService helloService;

    @Bean(name = "/hello")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(helloService);
        exporter.setServiceInterface(HelloService.class);
        return exporter;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
