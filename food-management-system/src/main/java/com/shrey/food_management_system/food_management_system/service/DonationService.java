package com.shrey.food_management_system.food_management_system.service;

import com.shrey.food_management_system.food_management_system.model.Donation;
import com.shrey.food_management_system.food_management_system.model.Ngo;
import com.shrey.food_management_system.food_management_system.repository.DonationRepository;
import com.shrey.food_management_system.food_management_system.repository.NgoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final NgoRepository ngoRepository;

    public DonationService(
            DonationRepository donationRepository,
            NgoRepository ngoRepository) {

        this.donationRepository = donationRepository;
        this.ngoRepository = ngoRepository;
    }

    // CREATE
    public Donation addDonation(Donation donation) {
        donation.setStatus("PENDING");
        return donationRepository.save(donation);
    }

    // READ ALL
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // READ BY ID
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Donation not found: " + id));
    }

    // GET ONLY PENDING DONATIONS (LIVE DONATIONS)
    public List<Donation> getPendingDonations() {
        return donationRepository.findByStatus("PENDING");
    }

    // UPDATE NORMAL DETAILS
    public Donation updateDonation(Long id, Donation updatedDonation) {

        Donation existing = getDonationById(id);

        existing.setDonorName(updatedDonation.getDonorName());
        existing.setQuantity(updatedDonation.getQuantity());
        existing.setFoodName(updatedDonation.getFoodName());
        existing.setFoodType(updatedDonation.getFoodType());
        existing.setRestaurant(updatedDonation.getRestaurant());

        return donationRepository.save(existing);
    }

    // NGO ACCEPTS DONATION
    public Donation acceptDonation(Long donationId, Long ngoId) {

        Donation donation = getDonationById(donationId);

        Ngo ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() ->
                        new RuntimeException("NGO not found: " + ngoId));

        donation.setNgo(ngo);
        donation.setStatus("ACCEPTED");

        return donationRepository.save(donation);
    }

    // DELETE
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}