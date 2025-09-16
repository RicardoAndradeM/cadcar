package io.github.ricardoandradem.cadcar.user.dto;

import io.github.ricardoandradem.cadcar.user.model.Role;
import io.github.ricardoandradem.cadcar.user.model.User;

import java.util.List;

public record UserResponseDTO(Long id, String name, String email, List<Role> roles) {
}
