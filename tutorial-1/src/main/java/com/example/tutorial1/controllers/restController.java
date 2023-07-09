package com.example.tutorial1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class restController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public Route");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute() {
        return ResponseEntity.ok("private route");
    }
}
