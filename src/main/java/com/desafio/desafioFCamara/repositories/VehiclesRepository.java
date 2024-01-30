package com.desafio.desafioFCamara.repositories;

import com.desafio.desafioFCamara.model.Vehicle;
import com.desafio.desafioFCamara.enums.VehicleTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> getVehiclesByCompany_Id(Long id);

    Long countDistinctByCompanyIdAndType(Long id, VehicleTypes types);

    List<Vehicle> getAllByCompanyIdAndType(Long id, VehicleTypes type);
}
