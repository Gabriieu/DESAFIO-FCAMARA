package com.desafio.desafioFCamara.dtos.company;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CompanyRequest {

    @NotEmpty(message = "name is mandatory")
    @Size(min = 2, message = "name must contain at least 2 characters")
    private String name;

    @NotEmpty(message = "CNPJ is mandatory")
    private String cnpj;

    @NotEmpty(message = "address is mandatory")
    @Size(min = 6, message = "address must contain at least 6 characters")
    private String address;

    @NotEmpty(message = "phone is mandatory ex: 119000000")
    @Size(min = 11, max = 11)
    private String phone;

    private int motorcycles_limit;

    private int cars_limit;
}
