package com.loom.virtualthread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

@Configuration
public class RestClientConfig {

    @Bean("blockingRestClient")
    public RestClient blockingRestClient() {
        return RestClient.create();
    }

    @Bean("nonBlockingRestClient")
    public RestClient nonBlockingRestClient() {
        return RestClient.builder()
                .requestFactory(
                        new JdkClientHttpRequestFactory(
                                HttpClient.newBuilder()
                                        .executor(Executors.newVirtualThreadPerTaskExecutor())
                                        .build()
                        )
                )
                .build();
    }
}
