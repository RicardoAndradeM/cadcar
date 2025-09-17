package io.github.ricardoandradem.cadcar.vehicle.repository;

import io.github.ricardoandradem.cadcar.vehicle.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
