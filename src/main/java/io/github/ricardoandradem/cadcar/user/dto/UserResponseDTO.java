package io.github.ricardoandradem.cadcar.user.dto;

import io.github.ricardoandradem.cadcar.user.model.Role;
import io.github.ricardoandradem.cadcar.user.model.User;
import io.github.ricardoandradem.cadcar.user.model.UserRole;

import java.util.List;

public record UserResponseDTO(Long id, String name, String email, List<Role> roles) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream().map(UserRole::getRole).toList()
        );
    }
}
