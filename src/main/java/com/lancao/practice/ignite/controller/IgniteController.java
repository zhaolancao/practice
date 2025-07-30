package com.lancao.practice.ignite.controller;

import com.lancao.practice.ignite.service.IgniteService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignite")
public class IgniteController {
    @Resource
    private IgniteService igniteService;

    @GetMapping("/newId")
    public ResponseEntity<Long> newId() {
        return ResponseEntity.ok(igniteService.newId());
    }
}
