package com.example.donationservice.controller;

import com.example.donationservice.dto.DonDTO;
import com.example.donationservice.model.Campagne;
import com.example.donationservice.repository.CampagneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DonationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CampagneRepository campagneRepository;

    private Campagne campagne;

    @BeforeEach
    void setUp() {
        campagneRepository.deleteAll();
        
        campagne = new Campagne();
        campagne.setNom("Test Campagne");
        campagne.setObjectifMontant(new BigDecimal("1000.00"));
        campagne.setDateDebut(LocalDate.now().minusDays(1));
        campagne.setDateFin(LocalDate.now().plusDays(1));
        campagne = campagneRepository.save(campagne);
    }

    @Test
    void getActiveCampagnes_ShouldReturnActiveCampagnes() throws Exception {
        mockMvc.perform(get("/api/campagnes/actives"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Test Campagne"))
                .andExpect(jsonPath("$[0].objectifMontant").value("1000.00"));
    }

    @Test
    void createDon_WithValidData_ShouldCreateDonation() throws Exception {
        DonDTO donDTO = new DonDTO();
        donDTO.setNomDonateur("John Doe");
        donDTO.setMontant(new BigDecimal("100.00"));

        mockMvc.perform(post("/api/campagnes/{id}/dons", campagne.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomDonateur").value("John Doe"))
                .andExpect(jsonPath("$.montant").value("100.00"))
                .andExpect(jsonPath("$.nomCampagne").value("Test Campagne"));
    }

    @Test
    void createDon_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        DonDTO donDTO = new DonDTO();
        donDTO.setNomDonateur("");  // Invalid: empty name
        donDTO.setMontant(new BigDecimal("-100.00"));  // Invalid: negative amount

        mockMvc.perform(post("/api/campagnes/{id}/dons", campagne.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donDTO)))
                .andExpect(status().isBadRequest());
    }
} 