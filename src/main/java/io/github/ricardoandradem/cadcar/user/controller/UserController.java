package io.github.ricardoandradem.cadcar.user.controller;

import io.github.ricardoandradem.cadcar.user.dto.RoleDTO;
import io.github.ricardoandradem.cadcar.user.dto.UserCreateDTO;
import io.github.ricardoandradem.cadcar.user.dto.UserResponseDTO;
import io.github.ricardoandradem.cadcar.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        var user = userService.createUser(userCreateDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<String> addRoleToUser(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        try {
            userService.addRoleToUser(id, roleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid Role.",HttpStatus.BAD_REQUEST);
        }
    }
}
