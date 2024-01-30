package com.desafio.desafioFCamara.services;

import com.desafio.desafioFCamara.dtos.vehicle.VehicleResponse;
import com.desafio.desafioFCamara.enums.VehicleTypes;
import com.desafio.desafioFCamara.model.Company;
import com.desafio.desafioFCamara.model.Vehicle;
import com.desafio.desafioFCamara.repositories.VehiclesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehiclesServices {

    private final VehiclesRepository VEHICLES_REPOSITORY;

    public VehiclesServices(VehiclesRepository vehiclesRepository) {
        this.VEHICLES_REPOSITORY = vehiclesRepository;
    }

    public Vehicle createVehicle(Company company, Vehicle vehicle) {

        if (vehicle.getType().equals(VehicleTypes.MOTORCYCLE)) {

            var companyMotorcycles = VEHICLES_REPOSITORY.countDistinctByCompanyIdAndType(company.getId(), VehicleTypes.MOTORCYCLE);

            if (companyMotorcycles < company.getMotorcycles_limit()) {
                vehicle.setCompany(company);
                return VEHICLES_REPOSITORY.save(vehicle);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Limit reached for creating motorcycles in the parking lot.");
            }

        } else if (vehicle.getType().equals(VehicleTypes.CAR)) {

            var companyCars = VEHICLES_REPOSITORY.countDistinctByCompanyIdAndType(company.getId(), VehicleTypes.CAR);

            if (companyCars < company.getCars_limit()) {
                vehicle.setCompany(company);
                return VEHICLES_REPOSITORY.save(vehicle);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Limit reached for creating cars in the parking lot.");
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid type.");
    }

    public List<Vehicle> getVehiclesByCompany(Company company) {
        return VEHICLES_REPOSITORY.getVehiclesByCompany_Id(company.getId());
    }

    public List<Vehicle> getVehicleByCompanyAndType(Company company, VehicleTypes type) {
        return VEHICLES_REPOSITORY.getAllByCompanyIdAndType(company.getId(), type);
    }

    public Vehicle getVehicleById(Long id) {
        var response = VEHICLES_REPOSITORY.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicle not found."));
        BeanUtils.copyProperties(response, VehicleResponse.class);
        return response;
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleData) {
        var vehicleFound = getVehicleById(id);

        vehicleFound.setBrand(vehicleData.getBrand());
        vehicleFound.setModel(vehicleData.getModel());
        vehicleFound.setColor(vehicleData.getColor());
        vehicleFound.setLicense_plate(vehicleData.getLicense_plate());
        vehicleFound.setType(vehicleData.getType());

        return VEHICLES_REPOSITORY.save(vehicleFound);
    }

    public void deleteVehicle(Long id) {
        var vehicleFound = getVehicleById(id);
        VEHICLES_REPOSITORY.delete(vehicleFound);
    }


}
