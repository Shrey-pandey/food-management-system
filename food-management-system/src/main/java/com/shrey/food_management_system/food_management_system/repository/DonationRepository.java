package com.shrey.food_management_system.food_management_system.repository;
import com.shrey.food_management_system.food_management_system.model.DonationStatus;
import com.shrey.food_management_system.food_management_system.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByStatus(DonationStatus status);
}