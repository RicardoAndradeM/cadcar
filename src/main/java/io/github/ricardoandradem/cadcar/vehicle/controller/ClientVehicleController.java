package io.github.ricardoandradem.cadcar.vehicle.controller;

import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/{clientId}/vehicle")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ClientVehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(
            @PathVariable Long clientId, @RequestBody VehicleCreateDTO vehicleCreateDTO){
        return new ResponseEntity<>(vehicleService.createVehicle(clientId, vehicleCreateDTO), HttpStatus.CREATED);
    }

}
