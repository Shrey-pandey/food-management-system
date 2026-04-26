package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.model.Ngo;
import com.shrey.food_management_system.food_management_system.service.NgoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ngos")
public class NgoController {

    private final NgoService ngoService;

    public NgoController(NgoService ngoService) {
        this.ngoService = ngoService;
    }

    // CREATE
    @PostMapping
    public Ngo add(@RequestBody Ngo ngo) {
        return ngoService.addNgo(ngo);
    }

    // READ ALL
    @GetMapping
    public List<Ngo> getAll() {
        return ngoService.getAllNgos();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Ngo getById(@PathVariable Long id) {
        return ngoService.getNgoById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Ngo update(@PathVariable Long id, @RequestBody Ngo ngo) {
        return ngoService.updateNgo(id, ngo);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        ngoService.deleteNgo(id);
        return "NGO deleted successfully";
    }
}