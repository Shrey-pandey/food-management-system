package com.shrey.food_management_system.food_management_system.repository;

import com.shrey.food_management_system.food_management_system.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    // show only live donations for NGO frontend
    List<Donation> findByStatus(String status);
}