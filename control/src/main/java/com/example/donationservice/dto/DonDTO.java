package com.example.donationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DonDTO {
    private Long id;
    
    @NotBlank(message = "Le nom de la campagne est obligatoire")
    private String nomCampagne;
    
    @NotBlank(message = "Le nom du donateur est obligatoire")
    private String nomDonateur;
    
    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;
    
    private LocalDateTime date;
} 