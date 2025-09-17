package io.github.ricardoandradem.cadcar.vehicle.dto;

import io.github.ricardoandradem.cadcar.vehicle.model.Brand;

public record BrandResponseDTO(Long id, String name) {
    public BrandResponseDTO(Brand brand) {
        this(
                brand.getId(),
                brand.getName()
        );
    }
}
