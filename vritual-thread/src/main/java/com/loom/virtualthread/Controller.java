package com.loom.virtualthread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class Controller {

    private final Service service;
    private final RestClient blockingRestClient;
    private final RestClient nonBlockingRestClient;

    public Controller(
        Service service,
        @Qualifier("blockingRestClient") RestClient blockingRestClient,
        @Qualifier("nonBlockingRestClient") RestClient nonBlockingRestClient
    ) {
        this.service = service;
        this.blockingRestClient = blockingRestClient;
        this.nonBlockingRestClient = nonBlockingRestClient;
    }

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

    @GetMapping("/non-block")
    public void requestNonBlock() {
        // 1. 가상 쓰레드에서 블로킹 작업을 수행하는 예시 -> 웹 요청이 바로 반환됨.
        Thread.ofVirtual().start(() -> {
            blockingRestClient.get()
                    .uri("http://localhost:4002/api/v0/sign-in/block")
                    .retrieve()
                    .body(String.class);

        });
    }

    @GetMapping("/block")
    public void requestBlock() {
        blockingRestClient.get()
                .uri("http://localhost:4002/api/v0/sign-in/block")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/block2")
    public void requestBlock2() {
        nonBlockingRestClient.get()
                .uri("http://localhost:4002/api/v0/sign-in/block")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/block3")
    public void requestBlock3() {
        blockingRestClient.get()
                .uri("http://localhost:4002/api/v0/sign-in/block")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/block4")
    public void requestBlock4() {
        nonBlockingRestClient.get()
                .uri("http://localhost:4002/api/v0/sign-in/block")
                .retrieve()
                .body(String.class);
    }
}
