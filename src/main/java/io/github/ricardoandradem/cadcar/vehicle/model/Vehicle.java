package io.github.ricardoandradem.cadcar.vehicle.model;

import io.github.ricardoandradem.cadcar.client.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "number_plate", unique = true, nullable = false)
    private String numberPlate;

    @Column(name = "chassis_number", unique = true, nullable = false)
    private String chassisNumber;

    @Column(name = "model_year", nullable = false)
    private Integer modelYear;

    @Column(name = "factory_year", nullable = false)
    private Integer factoryYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;
}
