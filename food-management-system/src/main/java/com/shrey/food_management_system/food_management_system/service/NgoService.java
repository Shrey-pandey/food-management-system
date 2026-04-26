package com.shrey.food_management_system.food_management_system.service;

import com.shrey.food_management_system.food_management_system.model.Ngo;
import com.shrey.food_management_system.food_management_system.repository.NgoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NgoService {

    private final NgoRepository ngoRepository;

    public NgoService(NgoRepository ngoRepository) {
        this.ngoRepository = ngoRepository;
    }

    // CREATE
    public Ngo addNgo(Ngo ngo) {
        return ngoRepository.save(ngo);
    }

    // READ ALL
    public List<Ngo> getAllNgos() {
        return ngoRepository.findAll();
    }

    // READ BY ID
    public Ngo getNgoById(Long id) {
        return ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found: " + id));
    }

    // UPDATE
    public Ngo updateNgo(Long id, Ngo updated) {

        Ngo existing = getNgoById(id);

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setContact(updated.getContact());

        return ngoRepository.save(existing);
    }

    // DELETE
    public void deleteNgo(Long id) {
        ngoRepository.deleteById(id);
    }
}