package io.github.ricardoandradem.cadcar.vehicle.controller;

import io.github.ricardoandradem.cadcar.vehicle.dto.ModelCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.ModelResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.ModelUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.service.ModelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@AllArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ModelResponseDTO> createModel(@RequestBody ModelCreateDTO modelCreateDTO) {
        return new ResponseEntity<>(modelService.createModel(modelCreateDTO) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ModelResponseDTO>> getAllModels() {
        return new ResponseEntity<>(modelService.getAllModels(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<ModelResponseDTO> updateModelById(@PathVariable Long id, @RequestBody ModelUpdateDTO modelUpdateDTO) {
        return new ResponseEntity<>(modelService.updateModelById(id, modelUpdateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModelById(@PathVariable Long id) {
        modelService.deleteModelById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
