package com.example.donationservice.controller;

import com.example.donationservice.dto.DonDTO;
import com.example.donationservice.projection.CampagneResume;
import com.example.donationservice.service.CampagneService;
import com.example.donationservice.service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Donations API", description = "API pour la gestion des campagnes et des dons")
public class DonationController {
    private final CampagneService campagneService;
    private final DonationService donationService;

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of(
            "message", "Bienvenue sur le service de gestion des dons",
            "documentation", "/swagger-ui.html",
            "h2-console", "/h2-console"
        ));
    }

    @GetMapping("/campagnes/actives")
    @Operation(summary = "Récupérer toutes les campagnes actives",
            description = "Retourne la liste des campagnes dont la date actuelle est comprise entre la date de début et la date de fin")
    public ResponseEntity<List<CampagneResume>> getActiveCampagnes() {
        return ResponseEntity.ok(campagneService.getActiveCampagnes());
    }

    @PostMapping("/campagnes/{id}/dons")
    @Operation(summary = "Enregistrer un nouveau don",
            description = "Enregistre un nouveau don pour une campagne spécifique")
    public ResponseEntity<DonDTO> createDon(
            @PathVariable Long id,
            @Valid @RequestBody DonDTO donDTO) {
        return ResponseEntity.ok(donationService.enregistrerDon(id, donDTO));
    }
} 