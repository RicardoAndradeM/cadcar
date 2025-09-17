package io.github.ricardoandradem.cadcar.vehicle.service;

import io.github.ricardoandradem.cadcar.vehicle.dto.BrandCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.BrandResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.BrandUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.exception.BrandNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.model.Brand;
import io.github.ricardoandradem.cadcar.vehicle.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandResponseDTO createBrand(BrandCreateDTO brandCreateDTO) {
        var brand = new Brand();
        brand.setName(brandCreateDTO.name());

        return new BrandResponseDTO(brandRepository.save(brand));
    }

    public List<BrandResponseDTO> getAllBrands() {
        return brandRepository.findAll().stream().map(BrandResponseDTO::new).toList();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found!"));
    }

    public BrandResponseDTO updateBrandById(Long id, BrandUpdateDTO brandUpdateDTO) {
        var brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found!"));

        Optional.ofNullable(brandUpdateDTO.name()).ifPresent(brand::setName);

        return new BrandResponseDTO(brandRepository.save(brand));
    }

    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
}
