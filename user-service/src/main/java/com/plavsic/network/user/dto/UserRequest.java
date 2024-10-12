package com.plavsic.network.user.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link com.plavsic.network.user.model.User}
 */
public record UserRequest(Long id, @NotNull @NotEmpty @NotBlank String username,
                          @NotNull @Size(min = 8, max = 20) String password,
                          @NotNull @NotEmpty @NotBlank String firstName,
                          @NotNull @NotEmpty @NotBlank String lastName,
                          @NotNull @Email @NotEmpty @NotBlank String email,
                          String profilePictureUrl){}