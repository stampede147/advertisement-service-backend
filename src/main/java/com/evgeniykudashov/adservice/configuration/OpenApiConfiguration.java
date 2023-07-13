package com.evgeniykudashov.adservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI groupedOpenApi() {
        Info info = new Info().title("Advertisement-service-api")
                .version("1.0")
                .contact(new Contact().email("stampede147@gmail.com").name("Evgeniy Kudashov"));
        List<Server> developmentServerIp = List.of(new Server()
                .description("development server ip")
                .url("localhost:8080"));
        return new OpenAPI()
                .info(info)
                .servers(developmentServerIp);
    }
}
