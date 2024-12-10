package com.loom.virtualthread;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime startTime = LocalDateTime.now();
        log.info("요청 시작 = " + startTime + "쓰레드 정보 = " + Thread.currentThread());
        long tic = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long toc = System.currentTimeMillis();
        LocalDateTime endTime = LocalDateTime.now();
        log.info("요청 종료 = " + endTime + "쓰레드 정보 = " + Thread.currentThread());
        log.info("소요 시간 = " + (toc - tic) + "ms");
    }
}
