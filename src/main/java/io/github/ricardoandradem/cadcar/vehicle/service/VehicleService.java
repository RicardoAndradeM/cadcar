package io.github.ricardoandradem.cadcar.vehicle.service;

import io.github.ricardoandradem.cadcar.client.repository.ClientRepository;
import io.github.ricardoandradem.cadcar.common.exception.ClientNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleCreateDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleResponseDTO;
import io.github.ricardoandradem.cadcar.vehicle.dto.VehicleUpdateDTO;
import io.github.ricardoandradem.cadcar.vehicle.exception.ChassisNumberAlreadyExistsException;
import io.github.ricardoandradem.cadcar.vehicle.exception.ModelNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.exception.NumberPlateAlreadyExistsException;
import io.github.ricardoandradem.cadcar.vehicle.exception.VehicleNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.model.Vehicle;
import io.github.ricardoandradem.cadcar.vehicle.repository.ModelRepository;
import io.github.ricardoandradem.cadcar.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final ModelRepository modelRepository;

    public VehicleResponseDTO createVehicle(Long clientId, VehicleCreateDTO vehicleCreateDTO) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found!"));
        var model = modelRepository.findById(vehicleCreateDTO.modelId())
                .orElseThrow(() -> new ModelNotFoundException("Model not found!"));

        validateVehicle(vehicleCreateDTO);

        var newVehicle = new Vehicle();
        newVehicle.setClient(client);
        newVehicle.setModel(model);
        newVehicle.setNumberPlate(vehicleCreateDTO.numberPlate());
        newVehicle.setChassisNumber(vehicleCreateDTO.chassisNumber());
        newVehicle.setModelYear(vehicleCreateDTO.modelYear().getValue());
        newVehicle.setFactoryYear(vehicleCreateDTO.factoryYear().getValue());
        newVehicle.setColor(vehicleCreateDTO.color());

        return new VehicleResponseDTO(vehicleRepository.save(newVehicle));
    }

    private void validateVehicle(VehicleCreateDTO vehicleCreateDTO) {
        if (vehicleRepository.existsByNumberPlate(vehicleCreateDTO.numberPlate())) {
            throw new NumberPlateAlreadyExistsException("The number plate provided is already in use");
        }
        if (vehicleRepository.existsByChassisNumber(vehicleCreateDTO.chassisNumber())) {
            throw new ChassisNumberAlreadyExistsException("The chassis number provided is already in use");
        }
    }

    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleResponseDTO::new).toList();
    }

    public VehicleResponseDTO getVehicleById(Long id) {
        return new VehicleResponseDTO(vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found!")));
    }

    public VehicleResponseDTO updateVehicleById(Long id, VehicleUpdateDTO vehicleUpdateDTO) {
        var vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found!"));

        if (vehicleUpdateDTO.modelId() != null) {
           var model = modelRepository.findById(vehicleUpdateDTO.modelId())
                   .orElseThrow(() -> new ModelNotFoundException("Model not found!"));

           vehicle.setModel(model);
        }

        Optional.ofNullable(vehicleUpdateDTO.numberPlate()).ifPresent(vehicle::setNumberPlate);
        Optional.ofNullable(vehicleUpdateDTO.chassisNumber()).ifPresent(vehicle::setChassisNumber);
        Optional.ofNullable(vehicleUpdateDTO.modelYear()).ifPresent(d -> vehicle.setModelYear(d.getValue()));
        Optional.ofNullable(vehicleUpdateDTO.factoryYear()).ifPresent(d -> vehicle.setFactoryYear(d.getValue()));
        Optional.ofNullable(vehicleUpdateDTO.color()).ifPresent(vehicle::setColor);

        return new VehicleResponseDTO(vehicleRepository.save(vehicle));
    }

    @Transactional
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteByVehicleId(id);
    }
}
