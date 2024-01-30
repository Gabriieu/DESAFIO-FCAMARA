package com.desafio.desafioFCamara.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;

@Entity
@Table(name = "companies")
@Data
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", nullable = false, unique = true)
    @CNPJ(groups = CNPJ.class)
    private String cnpj;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "motorcycles_limit", nullable = false)
    private int motorcycles_limit;

    @Column(name = "cars_limit", nullable = false)
    private int cars_limit;

}
