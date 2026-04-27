package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.model.Ngo;
import com.shrey.food_management_system.food_management_system.model.Donation;
import com.shrey.food_management_system.food_management_system.service.NgoService;
import com.shrey.food_management_system.food_management_system.service.DonationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/ngos")
public class NgoController {

    private final NgoService ngoService;
    private final DonationService donationService;

    public NgoController(NgoService ngoService,
                         DonationService donationService) {
        this.ngoService = ngoService;
        this.donationService = donationService;
    }

    // NGO accepts donation
    @PreAuthorize("hasRole('NGO')")
    @PutMapping("/{ngoId}/accept/{donationId}")
    public Donation acceptDonation(@PathVariable Long ngoId,
                                   @PathVariable Long donationId) {

        return donationService.acceptDonation(donationId, ngoId);
    }

    // READ ALL NGOs
    @GetMapping
    public List<Ngo> getAll() {
        return ngoService.getAllNgos();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Ngo getById(@PathVariable Long id) {
        return ngoService.getNgoById(id);
    }

    // UPDATE NGO
    @PutMapping("/{id}")
    public Ngo update(@PathVariable Long id, @RequestBody Ngo ngo) {
        return ngoService.updateNgo(id, ngo);
    }

    // DELETE NGO
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        ngoService.deleteNgo(id);
        return "NGO deleted successfully";
    }
}