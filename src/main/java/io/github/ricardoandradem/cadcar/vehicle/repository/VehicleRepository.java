package io.github.ricardoandradem.cadcar.vehicle.repository;

import io.github.ricardoandradem.cadcar.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByNumberPlate(String numberPlate);

    boolean existsByChassisNumber(String chassisNumber);

    @Modifying
    @Query("DELETE FROM Vehicle v WHERE v.id = :id")
    void deleteByVehicleId(@Param("id") Long id);
}
