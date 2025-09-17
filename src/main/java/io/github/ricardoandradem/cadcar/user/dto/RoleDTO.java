package io.github.ricardoandradem.cadcar.user.dto;

import io.github.ricardoandradem.cadcar.user.model.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleDTO(@NotBlank Role role) {
}
