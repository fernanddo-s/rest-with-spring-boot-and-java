package com.example.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Open API with Java")
                        .version("v1")
                        .description("API desenvolvida durante o curso de Spring com Java")
                        .termsOfService("url-da-pagina-de-termos-de-servico")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("url-da-pagina-de-licença")));
    }
}
