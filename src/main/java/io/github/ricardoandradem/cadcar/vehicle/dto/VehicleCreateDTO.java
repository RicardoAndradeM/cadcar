package io.github.ricardoandradem.cadcar.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.ricardoandradem.cadcar.vehicle.model.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Year;

public record VehicleCreateDTO(

        @NotBlank
        Long modelId,

        @NotBlank
        String numberPlate,

        @NotBlank
        String chassisNumber,

        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "yyyy")
        Year modelYear,

        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "yyyy")
        Year factoryYear,

        @NotNull
        Color color
) {
}
