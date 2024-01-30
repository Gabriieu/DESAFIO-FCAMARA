package com.desafio.desafioFCamara.controllers;

import com.desafio.desafioFCamara.dtos.vehicle.VehicleRequest;
import com.desafio.desafioFCamara.dtos.vehicle.VehicleResponse;
import com.desafio.desafioFCamara.mapper.Mapper;
import com.desafio.desafioFCamara.model.Vehicle;
import com.desafio.desafioFCamara.services.VehiclesServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {

    private final VehiclesServices VEHICLES_SERVICES;

    public VehiclesController(VehiclesServices vehiclesServices) {
        this.VEHICLES_SERVICES = vehiclesServices;
    }

    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable Long id) {
        var response = VEHICLES_SERVICES.getVehicleById(id);

        return Mapper.parseObject(response, VehicleResponse.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleResponse updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleRequest vehicle) {
        var dto = Mapper.parseObject(vehicle, Vehicle.class);
        var response = VEHICLES_SERVICES.updateVehicle(id, dto);

        return Mapper.parseObject(response, VehicleResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id) {
        VEHICLES_SERVICES.deleteVehicle(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final Map<String, String> handleValidationsException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
