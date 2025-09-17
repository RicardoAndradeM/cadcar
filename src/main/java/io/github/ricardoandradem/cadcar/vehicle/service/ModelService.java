package io.github.ricardoandradem.cadcar.vehicle.service;

import io.github.ricardoandradem.cadcar.vehicle.dto.ModelCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.ModelResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.ModelUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.exception.ModelNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.model.Model;
import io.github.ricardoandradem.cadcar.vehicle.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public ModelResponseDTO createModel(ModelCreateDTO modelCreateDTO) {
        var brand = brandService.getBrandById(modelCreateDTO.brandId());
        var model = new Model();
        model.setBrand(brand);
        model.setName(modelCreateDTO.name());

        return new ModelResponseDTO(modelRepository.save(model));
    }

    public List<ModelResponseDTO> getAllModels() {
        return modelRepository.findAll().stream().map(ModelResponseDTO::new).toList();
    }

    public ModelResponseDTO updateModelById(Long id, ModelUpdateDTO modelUpdateDTO) {
        var model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found!"));

        Optional.ofNullable(modelUpdateDTO.name()).ifPresent(model::setName);
        if (modelUpdateDTO.brandId() != null) {
            var brand = brandService.getBrandById(modelUpdateDTO.brandId());
            model.setBrand(brand);
        }

        return new ModelResponseDTO(modelRepository.save(model));
    }

    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }
}
