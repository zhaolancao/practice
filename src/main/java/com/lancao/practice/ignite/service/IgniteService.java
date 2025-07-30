package com.lancao.practice.ignite.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IgniteService {
    @Resource
    private IdGenerator idGenerator;

    public Long newId() {
        return idGenerator.uniqueId();
    }
}
