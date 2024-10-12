package com.plavsic.network.user.dto;

import com.plavsic.network.user.model.Role;
import com.plavsic.network.user.model.User;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */
public record UserResponse(Long id,
                           String username,
                           String password,
                           Set<Role> roles,
                           String firstName,
                           String lastName,
                           String email,
                           String profilePictureUrl){}