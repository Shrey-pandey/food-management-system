package com.shrey.food_management_system.food_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users") // better than using reserved word "user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // full name of person
    private String name;

    // used for login
    @Column(unique = true)
    private String email;

    // for authentication
    private String password;

    // ADMIN / RESTAURANT / NGO
    private String role;

    // admin approval system
    private boolean isApproved;
}
