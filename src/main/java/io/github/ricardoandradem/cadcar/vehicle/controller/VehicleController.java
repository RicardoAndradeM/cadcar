package io.github.ricardoandradem.cadcar.vehicle.controller;

import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicleById(@PathVariable Long id, @RequestBody VehicleUpdateDTO vehicleUpdateDTO) {
        return new ResponseEntity<>(vehicleService.updateVehicleById(id, vehicleUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
