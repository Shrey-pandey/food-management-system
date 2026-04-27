package com.shrey.food_management_system.food_management_system.service;
import com.shrey.food_management_system.food_management_system.model.*;
import com.shrey.food_management_system.food_management_system.repository.*;
import org.springframework.stereotype.Service;
import com.shrey.food_management_system.food_management_system.repository.UserRepository;
import java.util.List;
import com.shrey.food_management_system.food_management_system.model.DonationStatus;
import com.shrey.food_management_system.food_management_system.dto.DonationResponseDTO;
@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    public DonationService(
            DonationRepository donationRepository,
            UserRepository userRepository) {

        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public Donation addDonation(Donation donation) {
        donation.setStatus(DonationStatus.PENDING);
        return donationRepository.save(donation);
    }

    // READ ALL
    public List<DonationResponseDTO> getAllDonations() {

        List<Donation> donations = donationRepository.findAll();

        return donations.stream().map(donation -> {
            DonationResponseDTO dto = new DonationResponseDTO();

            dto.setId(donation.getId());
            dto.setDonorName(donation.getDonorName());
            dto.setFoodName(donation.getFoodName());
            dto.setQuantity(donation.getQuantity());
            dto.setFoodType(donation.getFoodType());
            dto.setStatus(donation.getStatus().name());

            if (donation.getRestaurant() != null) {
                dto.setRestaurantName(donation.getRestaurant().getName());
            }

            if (donation.getNgo() != null) {
                dto.setNgoName(donation.getNgo().getName());
            }

            return dto;
        }).toList();
    }

    // BY ID
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found: " + id));
    }

    // PENDING DONATIONS
    public List<Donation> getPendingDonations() {
        return donationRepository.findByStatus(DonationStatus.PENDING);
    }

    // UPDATE
    public Donation updateDonation(Long id, Donation updatedDonation) {

        Donation existing = getDonationById(id);

        existing.setDonorName(updatedDonation.getDonorName());
        existing.setQuantity(updatedDonation.getQuantity());
        existing.setFoodName(updatedDonation.getFoodName());
        existing.setFoodType(updatedDonation.getFoodType());

        User restaurant = userRepository.findById(updatedDonation.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        existing.setRestaurant(restaurant);

        return donationRepository.save(existing);
    }

    // NGO ACCEPTS
    public Donation acceptDonation(Long donationId, Long ngoId) {

        Donation donation = getDonationById(donationId);

        User ngo = userRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        donation.setNgo(ngo);
        donation.setStatus(DonationStatus.ACCEPTED);

        return donationRepository.save(donation);
    }

    // DELETE
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}