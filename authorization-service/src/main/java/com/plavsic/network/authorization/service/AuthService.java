package com.plavsic.network.authorization.service;

import com.plavsic.network.authorization.dto.LoginRequest;
import com.plavsic.network.authorization.dto.RegisterRequest;
import com.plavsic.network.authorization.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.plavsic.security.userDetails.CustomUserDetails;
import org.plavsic.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${secret}")
    private String secret;
    private final UserClient userClient;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),loginRequest.password())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(secret);
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.
                loadUserByUsername(loginRequest.username());
        return jwtTokenUtil.generateToken(customUserDetails);
    }

    public String signup(RegisterRequest registerRequest) {
        userClient.createUser(registerRequest);
        return "User created";
    }

}
