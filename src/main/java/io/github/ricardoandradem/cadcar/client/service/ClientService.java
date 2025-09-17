package io.github.ricardoandradem.cadcar.client.service;

import io.github.ricardoandradem.cadcar.client.dto.ClientCreateDTO;
import io.github.ricardoandradem.cadcar.client.dto.ClientResponseDTO;
import io.github.ricardoandradem.cadcar.client.dto.ClientUpdateDTO;
import io.github.ricardoandradem.cadcar.common.exception.ClientNotFoundException;
import io.github.ricardoandradem.cadcar.client.model.Client;
import io.github.ricardoandradem.cadcar.client.repository.ClientRepository;
import io.github.ricardoandradem.cadcar.client.exception.CpfAlreadyExistsException;
import io.github.ricardoandradem.cadcar.common.exception.EmailAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientResponseDTO createClient(ClientCreateDTO clientCreateDTO) {
        validateClient(clientCreateDTO);

        var newClient = new Client();
        newClient.setName(clientCreateDTO.name());
        newClient.setEmail(clientCreateDTO.email());
        newClient.setCpf(clientCreateDTO.cpf());
        newClient.setStreet(clientCreateDTO.street());
        newClient.setNeighborhood(clientCreateDTO.neighborhood());
        newClient.setCity(clientCreateDTO.city());
        newClient.setState(clientCreateDTO.state());
        newClient.setBirthday(clientCreateDTO.birthday());

        var savedClient = clientRepository.save(newClient);
        return new ClientResponseDTO(savedClient);
    }

    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientResponseDTO::new).toList();
    }

    private void validateClient(ClientCreateDTO clientCreateDTO) {
        if (clientRepository.existsByEmail(clientCreateDTO.email())) {
            throw new EmailAlreadyExistsException("The email provided is already in use.");
        }
        if (clientRepository.existsByCpf(clientCreateDTO.cpf())) {
            throw new CpfAlreadyExistsException("The CPF provided is already in use.");
        }
    }

    public ClientResponseDTO getClientById(Long id) {
        return new ClientResponseDTO(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found!")));
    }

    public ClientResponseDTO updateClientById(Long id, ClientUpdateDTO clientUpdateDTO) {
        var user = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found!"));

        Optional.ofNullable(clientUpdateDTO.name()).ifPresent(user::setName);
        Optional.ofNullable(clientUpdateDTO.email()).ifPresent(user::setEmail);
        Optional.ofNullable(clientUpdateDTO.cpf()).ifPresent(user::setCpf);
        Optional.ofNullable(clientUpdateDTO.street()).ifPresent(user::setStreet);
        Optional.ofNullable(clientUpdateDTO.neighborhood()).ifPresent(user::setNeighborhood);
        Optional.ofNullable(clientUpdateDTO.city()).ifPresent(user::setCity);
        Optional.ofNullable(clientUpdateDTO.state()).ifPresent(user::setState);
        Optional.ofNullable(clientUpdateDTO.birthday()).ifPresent(user::setBirthday);

        return new ClientResponseDTO(clientRepository.save(user));
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
