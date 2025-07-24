package com.loom.virtualthread.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void asyncMethod() {
        // Simulate some asynchronous processing
        try {
            if (Thread.currentThread().isVirtual()) {
                log.info("현재 [ 내부 ] 쓰레드는 Virtual Thread입니다.");
            } else {
                log.info("현재 [ 내부 ] 쓰레드는 일반 Thread입니다.");
            }
            log.info("비동기 쓰레드 시작  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
            Thread.sleep(100000); // Simulating a delay
            log.info("비동기 쓰레드 종료  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new RuntimeException("Async operation interrupted", e);
        }
    }
}
