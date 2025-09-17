package io.github.ricardoandradem.cadcar.vehicle.controller;

import io.github.ricardoandradem.cadcar.vehicle.dto.BrandCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.BrandResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.BrandUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.service.BrandService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandCreateDTO brandCreateDTO) {
        return new ResponseEntity<>(brandService.createBrand(brandCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<BrandResponseDTO>> getAllBrands(){
        return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrandById(@PathVariable Long id, @RequestBody BrandUpdateDTO brandUpdateDTO) {
        return new ResponseEntity<>(brandService.updateBrandById(id, brandUpdateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable Long id) {
        brandService.deleteBrandById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
