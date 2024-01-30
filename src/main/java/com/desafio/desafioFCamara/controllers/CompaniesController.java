package com.desafio.desafioFCamara.controllers;

import com.desafio.desafioFCamara.dtos.company.CompanyRequest;
import com.desafio.desafioFCamara.dtos.company.CompanyResponse;
import com.desafio.desafioFCamara.dtos.vehicle.VehicleRequest;
import com.desafio.desafioFCamara.dtos.vehicle.VehicleResponse;
import com.desafio.desafioFCamara.enums.VehicleTypes;
import com.desafio.desafioFCamara.mapper.Mapper;
import com.desafio.desafioFCamara.model.Company;
import com.desafio.desafioFCamara.model.Vehicle;
import com.desafio.desafioFCamara.services.CompaniesServices;
import com.desafio.desafioFCamara.services.VehiclesServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompaniesController {

    private final CompaniesServices COMPANY_SERVICES;
    private final VehiclesServices VEHICLES_SERVICES;

    CompaniesController(CompaniesServices companiesServices, VehiclesServices vehiclesServices) {
        this.COMPANY_SERVICES = companiesServices;
        this.VEHICLES_SERVICES = vehiclesServices;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody @Valid CompanyRequest companyData) {
        var dto = Mapper.parseObject(companyData, Company.class);
        var response = COMPANY_SERVICES.createCompany(dto);
        return Mapper.parseObject(response, CompanyResponse.class);
    }

    @PostMapping(value = "/{id}/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse registerVehicle(@PathVariable Long id, @RequestBody @Valid VehicleRequest vehicleData) {
        var company = COMPANY_SERVICES.getCompanyById(id);

        var vehicleDto = Mapper.parseObject(vehicleData, Vehicle.class);
        var vehicle = VEHICLES_SERVICES.createVehicle(company, vehicleDto);

        return Mapper.parseObject(vehicle, VehicleResponse.class);
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        var response = COMPANY_SERVICES.getCompanyById(id);

        return Mapper.parseObject(response, CompanyResponse.class);
    }

    @GetMapping("/{id}/vehicles")
    public List<VehicleResponse> getVehiclesByCompany(@PathVariable Long id, @RequestParam(required = false) VehicleTypes type) {
        var companyFound = COMPANY_SERVICES.getCompanyById(id);

        if (type == null) {
            var response = VEHICLES_SERVICES.getVehiclesByCompany(companyFound);

            return Mapper.parseListObjects(response, VehicleResponse.class);
        }

        var response = VEHICLES_SERVICES.getVehicleByCompanyAndType(companyFound, type);

        return Mapper.parseListObjects(response, VehicleResponse.class);

    }


    @GetMapping("/all")
    public List<CompanyResponse> getAllCompanies() {
        var response = COMPANY_SERVICES.getAllCompanies();
        return Mapper.parseListObjects(response, CompanyResponse.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CompanyResponse updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyRequest companyData) {
        var dto = Mapper.parseObject(companyData, Company.class);
        var response = COMPANY_SERVICES.updateCompany(id, dto);
        return Mapper.parseObject(response, CompanyResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        COMPANY_SERVICES.deleteCompany(id);
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
