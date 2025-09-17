package io.github.ricardoandradem.cadcar.client.controller;

import io.github.ricardoandradem.cadcar.client.dto.ClientCreateDTO;
import io.github.ricardoandradem.cadcar.client.dto.ClientResponseDTO;
import io.github.ricardoandradem.cadcar.client.dto.ClientUpdateDTO;
import io.github.ricardoandradem.cadcar.client.service.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientCreateDTO clientCreateDTO) {
        return new ResponseEntity<>(clientService.createClient(clientCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClientById(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO clientUpdateDTO) {
        return new ResponseEntity<>(clientService.updateClientById(id, clientUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
