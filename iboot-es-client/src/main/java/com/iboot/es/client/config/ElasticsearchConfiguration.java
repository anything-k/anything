package com.iboot.es.client.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * es 配置
 *
 * @author FanMingxin
 * @date 2019/1/3 17:24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@ConditionalOnProperty(prefix = "elasticsearch",value = {"enable"},havingValue = "true")
public class ElasticsearchConfiguration {

    private String host;

    private Integer port;

    private String username;

    private String password;

    @Bean
    public RestClient transportClient() throws UnknownHostException {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        return RestClient.builder(new HttpHost(host,port))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
                .setRequestConfigCallback(requestConfigBuilder->{
                                requestConfigBuilder.setConnectTimeout(5*60*1000);
                                requestConfigBuilder.setSocketTimeout(5*60*1000);
                                requestConfigBuilder.setConnectionRequestTimeout(5*1000);
                                return requestConfigBuilder;
                        }).build();
    }
}
