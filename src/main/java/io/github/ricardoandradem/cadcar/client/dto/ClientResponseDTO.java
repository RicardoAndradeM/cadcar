package io.github.ricardoandradem.cadcar.client.dto;

import io.github.ricardoandradem.cadcar.client.model.Client;
import io.github.ricardoandradem.cadcar.client.model.State;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.model.Vehicle;

import java.time.LocalDate;
import java.util.List;

public record ClientResponseDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String street,
        String neighborhood,
        String city,
        State state,
        LocalDate birthday,
        List<VehicleResponseDTO> vehicles
) {
    public ClientResponseDTO(Client client) {
        this(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getStreet(),
                client.getNeighborhood(),
                client.getCity(),
                client.getState(),
                client.getBirthday(),
                client.getVehicles().stream().map(VehicleResponseDTO::new).toList()
        );
    }
}
