package com.anything.boot.feign.config;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/10 18:19
 */
@Configuration
public class MyConfig {

    @Bean
    public Request.Options options(){
        return new Request.Options(5000, 5000);
    }

    @Bean
    public RequestInterceptor interceptor(){
        return new MyInterceptor();
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(8,10,3);
    }

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Decoder decod(){
        return new MyDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters)));
    }

    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/

}
