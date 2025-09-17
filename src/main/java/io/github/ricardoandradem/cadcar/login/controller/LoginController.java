package io.github.ricardoandradem.cadcar.login.controller;

import io.github.ricardoandradem.cadcar.login.dto.LoginRequestDTO;
import io.github.ricardoandradem.cadcar.login.dto.TokenDTO;
import io.github.ricardoandradem.cadcar.login.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<>(loginService.login(loginRequestDTO), HttpStatus.CREATED);
    }
}
