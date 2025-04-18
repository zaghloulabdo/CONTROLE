package com.example.donationservice.repository;

import com.example.donationservice.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
} 