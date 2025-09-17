package io.github.ricardoandradem.cadcar.vehicle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModelCreateDTO(@NotNull Long brandId, @NotBlank String name) {
}
