package com.desafio.desafioFCamara;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST para gerenciamento de estacionamentos de carros e motos.")
                ).externalDocs(
                        new ExternalDocumentation()
                                .description("José Gabriel")
                                .url("https://jose-gabriel-portifolio.vercel.app/"));
    }
}
