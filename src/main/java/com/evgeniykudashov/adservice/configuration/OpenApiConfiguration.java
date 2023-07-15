package com.evgeniykudashov.adservice.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.RouterOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SecuritySchemes({
        @SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "jwt authentication",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.COOKIE
        )
})

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI groupedOpenApi() {
        Info info = new Info().title("Advertisement-service-api")
                .version("1.0-BETA")
                .contact(new Contact().email("stampede147@gmail.com").name("Evgeniy Kudashov"));
        List<Server> developmentServerIp = List.of(new Server()
                .description("development server ip")
                .url("localhost:8080"));
        return new OpenAPI()
                .info(info)
                .servers(developmentServerIp);
    }

    @Bean
    public RouterOperationCustomizer routerOperationCustomizer() {
        return (routerOperation, handlerMethod) -> {
            if (routerOperation.getParams().length > 0) {
                routerOperation.setPath(routerOperation.getPath() + "?" + String.join("&", routerOperation.getParams()));
            }
            return routerOperation;
        };
    }
}
