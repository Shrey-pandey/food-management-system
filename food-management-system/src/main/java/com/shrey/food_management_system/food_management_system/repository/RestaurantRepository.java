package com.shrey.food_management_system.food_management_system.repository;

import com.shrey.food_management_system.food_management_system.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}