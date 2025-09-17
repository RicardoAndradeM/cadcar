package io.github.ricardoandradem.cadcar.vehicle.dto;

import io.github.ricardoandradem.cadcar.vehicle.model.Color;
import io.github.ricardoandradem.cadcar.vehicle.model.Vehicle;

public record VehicleResponseDTO(
        Long id,
        Long clientId,
        ModelResponseDTO model,
        String numberPlate,
        String chassisNumber,
        Integer modelYear,
        Integer factoryYear,
        Color color
) {
    public VehicleResponseDTO(Vehicle vehicle){
        this(
                vehicle.getId(),
                vehicle.getClient().getId(),
                new ModelResponseDTO(vehicle.getModel()),
                vehicle.getNumberPlate(),
                vehicle.getChassisNumber(),
                vehicle.getModelYear(),
                vehicle.getFactoryYear(),
                vehicle.getColor()
        );
    }
}
