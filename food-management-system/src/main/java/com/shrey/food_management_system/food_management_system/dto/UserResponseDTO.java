package com.shrey.food_management_system.food_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean approved;
}
