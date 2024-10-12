package com.plavsic.network.user.service;


import com.plavsic.network.user.dto.UserRequest;
import com.plavsic.network.user.dto.UserResponse;

@org.springframework.stereotype.Service
public interface UserService extends Service<UserRequest,UserResponse> {

}
