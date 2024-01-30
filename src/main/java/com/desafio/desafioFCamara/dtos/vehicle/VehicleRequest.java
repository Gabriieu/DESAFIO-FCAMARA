package com.desafio.desafioFCamara.dtos.vehicle;

import com.desafio.desafioFCamara.enums.VehicleTypes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleRequest {

    @Valid

    @NotEmpty(message = "brand is mandatory")
    @Size(min = 3)
    private String brand;
    @NotEmpty(message = "model is mandatory")
    @Size(min = 3)
    private String model;
    @NotEmpty(message = "color is mandatory")
    @Size(min = 3)
    private String color;
    @NotEmpty(message = "license_plate is mandatory")
    @Size(min = 7, max = 7, message = "license_plate must contain 7 characters (Mercosul model plate)")
    private String license_plate;
    @Enumerated(EnumType.STRING)
    private VehicleTypes type;
}
