package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.model.Donation;
import com.shrey.food_management_system.food_management_system.service.DonationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // CREATE
    @PostMapping
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    // READ ALL
    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    // SHOW ONLY LIVE DONATIONS
    @GetMapping("/pending")
    public List<Donation> getPendingDonations() {
        return donationService.getPendingDonations();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Donation updateDonation(
            @PathVariable Long id,
            @RequestBody Donation updatedDonation) {

        return donationService.updateDonation(id, updatedDonation);
    }

    // NGO ACCEPTS DONATION
    @PutMapping("/{donationId}/accept/{ngoId}")
    public Donation acceptDonation(
            @PathVariable Long donationId,
            @PathVariable Long ngoId) {

        return donationService.acceptDonation(donationId, ngoId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "Donation deleted successfully";
    }
}