package com.desafio.desafioFCamara.model;

import com.desafio.desafioFCamara.enums.VehicleTypes;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String license_plate;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleTypes type;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

}
