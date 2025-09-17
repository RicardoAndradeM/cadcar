package io.github.ricardoandradem.cadcar.vehicle.repository;

import io.github.ricardoandradem.cadcar.vehicle.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
