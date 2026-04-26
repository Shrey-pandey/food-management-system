package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.model.Restaurant;
import com.shrey.food_management_system.food_management_system.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // CREATE
    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    // READ ALL
    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return "Deleted successfully";
    }
}