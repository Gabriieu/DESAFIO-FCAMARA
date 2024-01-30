package com.desafio.desafioFCamara.dtos.company;

import lombok.Data;

@Data
public class CompanyResponse {

    private Long id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;
    private int motorcycles_limit;
    private int cars_limit;
}
