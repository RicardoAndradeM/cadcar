package io.github.ricardoandradem.cadcar.user.service;

import io.github.ricardoandradem.cadcar.user.dto.RoleDTO;
import io.github.ricardoandradem.cadcar.user.dto.UserRegisterDTO;
import io.github.ricardoandradem.cadcar.user.dto.UserResponseDTO;
import io.github.ricardoandradem.cadcar.user.exception.EmailAlreadyExistsException;
import io.github.ricardoandradem.cadcar.user.exception.UserNotFoundException;
import io.github.ricardoandradem.cadcar.user.model.Role;
import io.github.ricardoandradem.cadcar.user.model.User;
import io.github.ricardoandradem.cadcar.user.model.UserRole;
import io.github.ricardoandradem.cadcar.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO save(UserRegisterDTO userRegisterDTO) {
        if (!userRepository.existsByEmail(userRegisterDTO.email())) {
            var newUser = new User();
            newUser.setName(userRegisterDTO.name());
            newUser.setEmail(userRegisterDTO.email());
            newUser.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

            newUser.setRoles(new ArrayList<>());
            var userRole = new UserRole();
            userRole.setRole(Role.USER);
            userRole.setUser(newUser);
            newUser.getRoles().add(userRole);

            var savedUser = userRepository.save(newUser);
            var savedUserRoles = savedUser.getRoles().stream().map(UserRole::getRole).toList();

            return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUserRoles);
        } else {
            throw new EmailAlreadyExistsException("The email provided is already in use.");
        }
    }

    public void addRoleToUser(Long id, RoleDTO roleDTO) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        var roleToAdd = new UserRole();
        roleToAdd.setRole(Role.valueOf(roleDTO.roleName()));
        roleToAdd.setUser(user);

        if (!user.getRoles().contains(roleToAdd)) {
            user.getRoles().add(roleToAdd);
            userRepository.save(user);
        }
    }
}