package com.example.donationservice.service;

import com.example.donationservice.projection.CampagneResume;
import com.example.donationservice.repository.CampagneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CampagneServiceTest {

    @Mock
    private CampagneRepository campagneRepository;

    @InjectMocks
    private CampagneService campagneService;

    @Mock
    private CampagneResume campagneResume;

    @BeforeEach
    void setUp() {
        when(campagneRepository.findActiveCampagnes(any(LocalDate.class)))
                .thenReturn(List.of(campagneResume));
    }

    @Test
    void getActiveCampagnes_ShouldReturnListOfActiveCampagnes() {
        // When
        List<CampagneResume> result = campagneService.getActiveCampagnes();

        // Then
        assertThat(result).isNotEmpty()
                         .hasSize(1)
                         .contains(campagneResume);
        verify(campagneRepository).findActiveCampagnes(any(LocalDate.class));
    }
} 