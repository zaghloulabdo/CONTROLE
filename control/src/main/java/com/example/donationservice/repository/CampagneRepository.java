package com.example.donationservice.repository;

import com.example.donationservice.model.Campagne;
import com.example.donationservice.projection.CampagneResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CampagneRepository extends JpaRepository<Campagne, Long> {
    @Query("SELECT c FROM Campagne c WHERE c.dateDebut <= :date AND c.dateFin >= :date")
    List<CampagneResume> findActiveCampagnes(LocalDate date);
} 