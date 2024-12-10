package com.loom.virtualthread;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class RandomUserGenerateTest {

    @Autowired Service service;

    @Test
    void generateUser() {
        for (int i = 0; i < 10000; i++) {
            String username = UUID.randomUUID().toString();
            service.create(username);
        }
    }

}
