package com.shrey.food_management_system.food_management_system.repository;

import com.shrey.food_management_system.food_management_system.model.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, Long> {
}