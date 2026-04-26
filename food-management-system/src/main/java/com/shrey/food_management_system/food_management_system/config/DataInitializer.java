package com.shrey.food_management_system.food_management_system.config;

import com.shrey.food_management_system.food_management_system.model.User;
import com.shrey.food_management_system.food_management_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("DATA INITIALIZER IS RUNNING");

        try {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

                System.out.println("CREATING ADMIN...");

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                admin.setApproved(true);

                userRepository.save(admin);

                System.out.println("ADMIN CREATED SUCCESSFULLY");
            } else {
                System.out.println("ADMIN ALREADY EXISTS");
            }

        } catch (Exception e) {
            System.out.println("ERROR WHILE CREATING ADMIN:");
            e.printStackTrace();
        }
    }
}