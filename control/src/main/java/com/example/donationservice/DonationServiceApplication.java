package com.example.donationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Service de Gestion des Dons",
        version = "1.0",
        description = "API pour la gestion des campagnes de dons et des transactions"
    )
)
public class DonationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonationServiceApplication.class, args);
    }
} 