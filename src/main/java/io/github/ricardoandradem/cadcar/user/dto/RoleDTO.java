package io.github.ricardoandradem.cadcar.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(@NotBlank String roleName) {
}
