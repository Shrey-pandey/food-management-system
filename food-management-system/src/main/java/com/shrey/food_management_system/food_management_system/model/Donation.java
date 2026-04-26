package com.shrey.food_management_system.food_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // donor details
    private String donorName;

    // food details
    private Integer quantity;
    private String foodName;
    private String foodType;

    // donation status
    // Example: PENDING, ACCEPTED, PICKED_UP, DELIVERED
    private String status;

    // Restaurant that created donation
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // NGO that accepts donation
    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;
}