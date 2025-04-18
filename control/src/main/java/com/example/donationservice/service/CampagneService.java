package com.example.donationservice.service;

import com.example.donationservice.projection.CampagneResume;
import com.example.donationservice.repository.CampagneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampagneService {
    private final CampagneRepository campagneRepository;

    @Transactional(readOnly = true)
    public List<CampagneResume> getActiveCampagnes() {
        return campagneRepository.findActiveCampagnes(LocalDate.now());
    }
} 