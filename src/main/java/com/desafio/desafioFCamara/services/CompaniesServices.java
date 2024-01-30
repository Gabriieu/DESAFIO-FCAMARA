package com.desafio.desafioFCamara.services;

import com.desafio.desafioFCamara.exceptions.ResourceNotFoundException;
import com.desafio.desafioFCamara.model.Company;
import com.desafio.desafioFCamara.repositories.CompaniesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompaniesServices {

    private final CompaniesRepository COMPANIES_REPOSITORY;

    CompaniesServices(CompaniesRepository companiesRepository) {
        this.COMPANIES_REPOSITORY = companiesRepository;
    }

    public Company createCompany(Company companyData) {
        return COMPANIES_REPOSITORY.save(companyData);
    }

    public Company getCompanyById(Long id) {
        return COMPANIES_REPOSITORY.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "company not found."));
    }

    public List<Company> getAllCompanies() {
        return COMPANIES_REPOSITORY.findAll();
    }

    public Company updateCompany(Long companyId, Company companyData) {
        Company company = getCompanyById(companyId);

        company.setName(companyData.getName());
        company.setCnpj(companyData.getCnpj());
        company.setAddress(companyData.getAddress());
        company.setPhone(companyData.getPhone());
        company.setMotorcycles_limit(companyData.getMotorcycles_limit());
        company.setCars_limit(companyData.getCars_limit());

        return COMPANIES_REPOSITORY.save(company);
    }

    public void deleteCompany(Long companyId) {
        var companyFound = getCompanyById(companyId);
        COMPANIES_REPOSITORY.delete(companyFound);
    }
}
