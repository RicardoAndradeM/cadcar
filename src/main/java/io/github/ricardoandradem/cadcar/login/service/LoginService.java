package io.github.ricardoandradem.cadcar.login.service;

import io.github.ricardoandradem.cadcar.login.dto.LoginRequestDTO;
import io.github.ricardoandradem.cadcar.login.dto.TokenDTO;
import io.github.ricardoandradem.cadcar.login.exception.IncorrectCredentialsException;
import io.github.ricardoandradem.cadcar.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;
    private final JwtService jwtService;

    public TokenDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password())
            );
        } catch (Exception e) {
            throw new IncorrectCredentialsException("Incorrect credentials.", e);
        }

        var userDetails = userDetailService.loadUserByUsername(loginRequestDTO.email());
        return new TokenDTO("Bearer " + jwtService.generateToken(userDetails));
    }
}
