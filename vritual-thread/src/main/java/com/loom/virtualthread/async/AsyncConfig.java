package com.loom.virtualthread.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {


    //1 기본은 ThreadPoolTaskExecutor를 사용
    //2 SimpleAsyncTaskExecutor를 사용하여 비동기 작업을 처리할 수 있습니다.
    //3. spring.virtualthread.enabled=true를 설정하면 SimpleAsyncTaskExecutor가 Virtual Thread를 사용하여 비동기 작업을 처리합니다.
//    @Override
//    public Executor getAsyncExecutor() {
//        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
//        executor.setThreadNamePrefix("AsyncExecutor-");
//        return executor;
//    }
}
