package com.desafio.desafioFCamara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class StartUp {

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }


}
