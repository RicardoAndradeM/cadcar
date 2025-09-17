package io.github.ricardoandradem.cadcar.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.ricardoandradem.cadcar.client.model.State;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClientCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @CPF
        @NotBlank
        String cpf,

        String street,
        String neighborhood,
        String city,
        State state,

        @Past
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthday
) {
}