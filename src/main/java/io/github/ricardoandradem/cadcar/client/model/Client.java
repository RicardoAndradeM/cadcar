package io.github.ricardoandradem.cadcar.client.model;

import io.github.ricardoandradem.cadcar.vehicle.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String street;
    private String neighborhood;
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles = new ArrayList<>();
}
