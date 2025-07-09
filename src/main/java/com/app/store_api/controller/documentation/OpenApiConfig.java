package com.app.store_api.controller.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Store API")
                                .version("v1")
                                .description("API for managing customers, products and sales.")
                )
                .servers(
                        List.of(
                                new Server()
                                .url("https://localhost:8080/api/v1")
                                .description("Local environment")
                        )
                );
    }
}
