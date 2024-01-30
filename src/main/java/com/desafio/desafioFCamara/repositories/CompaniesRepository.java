package com.desafio.desafioFCamara.repositories;

import com.desafio.desafioFCamara.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompaniesRepository extends JpaRepository<Company, Long> {
}
