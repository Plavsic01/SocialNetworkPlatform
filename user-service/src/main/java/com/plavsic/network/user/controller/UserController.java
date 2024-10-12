package com.plavsic.network.user.controller;

import com.plavsic.network.user.dto.UserRequest;
import com.plavsic.network.user.dto.UserResponse;
import com.plavsic.network.user.service.UserService;
import com.plavsic.network.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username) {
        UserServiceImpl userServiceImpl = (UserServiceImpl) userService;
        return new ResponseEntity<>(userServiceImpl.findByUsername(username),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.save(userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
