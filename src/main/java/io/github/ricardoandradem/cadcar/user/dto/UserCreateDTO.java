package io.github.ricardoandradem.cadcar.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {}
