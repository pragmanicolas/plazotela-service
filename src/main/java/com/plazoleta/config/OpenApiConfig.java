package com.plazoleta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("API de Plazoleta de Comidas")
                .version("1.0")
                .description("API para gestionar restaurantes, platos y pedidos en la plazoleta de comidas"));
    }
}
