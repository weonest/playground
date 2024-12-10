package com.loom.virtualthread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class Controller {

    private final Service service;

    @GetMapping("")
    public void getUsers() {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        executorService.submit(() -> {
            try {
                log.info("가상 쓰레드 시작  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
                service.create(UUID.randomUUID().toString());
                log.info("가상 쓰레드 종료  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                executorService.close();
            }
        });
    }
}
