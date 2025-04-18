package com.example.donationservice.service;

import com.example.donationservice.dto.DonDTO;
import com.example.donationservice.model.Campagne;
import com.example.donationservice.model.Donation;
import com.example.donationservice.repository.CampagneRepository;
import com.example.donationservice.repository.DonationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final CampagneRepository campagneRepository;

    @Transactional
    public DonDTO enregistrerDon(Long campagneId, DonDTO donDTO) {
        Campagne campagne = campagneRepository.findById(campagneId)
                .orElseThrow(() -> new EntityNotFoundException("Campagne non trouvée avec l'ID: " + campagneId));

        Donation donation = new Donation();
        donation.setCampagne(campagne);
        donation.setNomDonateur(donDTO.getNomDonateur());
        donation.setMontant(donDTO.getMontant());
        
        donation = donationRepository.save(donation);
        
        return convertToDTO(donation);
    }

    private DonDTO convertToDTO(Donation donation) {
        DonDTO dto = new DonDTO();
        dto.setId(donation.getId());
        dto.setNomCampagne(donation.getCampagne().getNom());
        dto.setNomDonateur(donation.getNomDonateur());
        dto.setMontant(donation.getMontant());
        dto.setDate(donation.getDate());
        return dto;
    }
} 