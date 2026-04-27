package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.model.Donation;
import com.shrey.food_management_system.food_management_system.service.DonationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.shrey.food_management_system.food_management_system.dto.DonationResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // CREATE DONATION (Restaurant only)
    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping
    public String createDonation(@RequestBody Donation donation) {
        donationService.addDonation(donation);
        return "Donation created successfully";
    }

    // READ ALL DONATIONS (Admin + NGO)
    @PreAuthorize("hasAnyRole('ADMIN', 'NGO')")
    @GetMapping
    public List<DonationResponseDTO> getAllDonations() {
        return donationService.getAllDonations();
    }
    // READ BY ID
    @PreAuthorize("hasAnyRole('ADMIN', 'NGO', 'RESTAURANT')")
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    // SHOW ONLY PENDING DONATIONS
    @PreAuthorize("hasAnyRole('ADMIN', 'NGO')")
    @GetMapping("/pending")
    public List<Donation> getPendingDonations() {
        return donationService.getPendingDonations();
    }

    // UPDATE DONATION
    @PreAuthorize("hasRole('RESTAURANT')")
    @PutMapping("/{id}")
    public Donation updateDonation(
            @PathVariable Long id,
            @RequestBody Donation updatedDonation) {

        return donationService.updateDonation(id, updatedDonation);
    }

    // NGO ACCEPTS DONATION
    @PreAuthorize("hasRole('NGO')")
    @PutMapping("/{donationId}/accept/{ngoId}")
    public Donation acceptDonation(
            @PathVariable Long donationId,
            @PathVariable Long ngoId) {

        return donationService.acceptDonation(donationId, ngoId);
    }

    // DELETE DONATION (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "Donation deleted successfully";
    }
}