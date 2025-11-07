package com.workspace.bass.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server localServer = new Server()
                .description("Local ENV")
                .url("http://localhost:9090");

        Server prodServer = new Server()
                .description("PROD ENV")
                .url("https://bassdiaw.netlify.app/");

        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation - Gestion ticket")
                        .description("Documentation de l'API avec Swagger et Spring Boot 3")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Bassirou Diaw")
                                .email("thasbass.dev@outlook.com")
                                .url("https://bassdiaw.netlify.app/")
                        )
                )
                .servers(List.of(localServer, prodServer))
                .components(new Components());
    }
}
