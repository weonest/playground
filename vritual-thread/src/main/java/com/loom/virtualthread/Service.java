package com.loom.virtualthread;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final Repository repository;

    @Transactional
    public void create(String username) {
        repository.save(new User(username));
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return repository.findAll();
    }

}
