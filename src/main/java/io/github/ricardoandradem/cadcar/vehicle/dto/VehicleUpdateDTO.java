package io.github.ricardoandradem.cadcar.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.ricardoandradem.cadcar.vehicle.model.Color;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Year;

public record VehicleUpdateDTO(

        Long modelId,
        String numberPlate,
        String chassisNumber,

        @PastOrPresent
        @JsonFormat(pattern = "yyyy")
        Year modelYear,

        @PastOrPresent
        @JsonFormat(pattern = "yyyy")
        Year factoryYear,

        Color color
) {
}
