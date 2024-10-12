package com.plavsic.network.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @RequestMapping("/**")
    public ResponseEntity<String> handleInvalidUrls() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("404 Not Found: The requested URL was not found on this server.");
    }
}
