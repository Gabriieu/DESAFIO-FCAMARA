package com.desafio.desafioFCamara.dtos.vehicle;

import com.desafio.desafioFCamara.enums.VehicleTypes;
import lombok.Data;

@Data
public class VehicleResponse {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private String license_plate;
    private VehicleTypes type;
}
