package io.github.ricardoandradem.cadcar.client.repository;

import io.github.ricardoandradem.cadcar.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
