package io.github.ricardoandradem.cadcar.vehicle.dto;

import io.github.ricardoandradem.cadcar.vehicle.model.Model;

public record ModelResponseDTO(Long id, BrandResponseDTO brand, String name) {
    public ModelResponseDTO(Model model) {
        this(
                model.getId(),
                new BrandResponseDTO(model.getBrand()),
                model.getName()
        );
    }
}
