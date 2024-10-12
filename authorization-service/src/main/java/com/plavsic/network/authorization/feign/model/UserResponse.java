package com.plavsic.network.authorization.feign.model;

import java.util.Set;

public record UserResponse(Long id, String username, String password, Set<RoleResponse> roles) {}
