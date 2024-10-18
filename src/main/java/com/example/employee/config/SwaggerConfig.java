package com.example.employee.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Configuration class for Swagger/OpenAPI integration in the Employee.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi specification - Employee",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8082"
                )
        }
)
@Configuration
public class SwaggerConfig {

}
