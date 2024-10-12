package com.plavsic.network.authorization.controller;

import com.plavsic.network.authorization.dto.LoginRequest;
import com.plavsic.network.authorization.dto.LoginResponse;
import com.plavsic.network.authorization.dto.RegisterRequest;
import com.plavsic.network.authorization.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {
        return new ResponseEntity<>(new LoginResponse(authService.login(login)), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest register){
        return new ResponseEntity<>(authService.signup(register),HttpStatus.OK);
    }


}
