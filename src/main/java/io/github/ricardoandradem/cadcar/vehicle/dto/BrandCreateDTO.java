package io.github.ricardoandradem.cadcar.vehicle.dto;

import jakarta.validation.constraints.NotBlank;

public record BrandCreateDTO(@NotBlank String name) {
}
