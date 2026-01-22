package com.bank.loancaixa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI loanApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan API")
                        .description("REST API for managing personal loan applications")
                        .version("1.0.0"));
    }
}