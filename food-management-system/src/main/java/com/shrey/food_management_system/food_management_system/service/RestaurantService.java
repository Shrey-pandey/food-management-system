package com.shrey.food_management_system.food_management_system.service;

import com.shrey.food_management_system.food_management_system.model.Restaurant;
import com.shrey.food_management_system.food_management_system.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // CREATE
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // READ ALL
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // READ BY ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
    }

    // UPDATE
    public Restaurant updateRestaurant(Long id, Restaurant updated) {

        Restaurant existing = getRestaurantById(id);

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setContact(updated.getContact());

        return restaurantRepository.save(existing);
    }

    // DELETE
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}