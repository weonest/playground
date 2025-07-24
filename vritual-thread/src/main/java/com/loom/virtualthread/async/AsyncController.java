package com.loom.virtualthread.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/async")
public class AsyncController {

    private final AsyncService service;

    @GetMapping("")
    public void async() {
        if (Thread.currentThread().isVirtual()) {
            log.info("현재 쓰레드는 Virtual Thread입니다.");
        } else {
            log.info("현재 쓰레드는 일반 Thread입니다.");
        }

        log.info("메인 쓰레드 시작  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
        service.asyncMethod();
        log.info("메인 쓰레드 종료  =  " + LocalDateTime.now() + " 쓰레드 정보  =  " + Thread.currentThread());
    }
}
