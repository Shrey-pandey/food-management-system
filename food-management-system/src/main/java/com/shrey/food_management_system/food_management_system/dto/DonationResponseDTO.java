package com.shrey.food_management_system.food_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationResponseDTO {

    private Long id;
    private String donorName;
    private String foodName;
    private Integer quantity;
    private String foodType;
    private String status;

    private String restaurantName;
    private String ngoName;
}
