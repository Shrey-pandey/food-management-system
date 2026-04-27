package com.shrey.food_management_system.food_management_system.service;
import com.shrey.food_management_system.food_management_system.dto.UserResponseDTO;
import com.shrey.food_management_system.food_management_system.model.User;
import com.shrey.food_management_system.food_management_system.repository.UserRepository;
import com.shrey.food_management_system.food_management_system.util.JWTutil;
import org.springframework.stereotype.Service;
import com.shrey.food_management_system.food_management_system.dto.AuthResponse;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER USER
    public User registerUser(User user) {

        // default approval false
        user.setApproved(false);

        // fix role mapping
        if (user.getRole().equalsIgnoreCase("RESTAURANT")) {
            user.setRole("ROLE_RESTAURANT");
        }
        else if (user.getRole().equalsIgnoreCase("NGO")) {
            user.setRole("ROLE_NGO");
        }
        else if (user.getRole().equalsIgnoreCase("ADMIN")) {
            user.setRole("ROLE_ADMIN");
        }
        else {
            throw new RuntimeException("Invalid role");
        }

        return userRepository.save(user);
    }
    // GET ALL USERS
    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();

            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            dto.setApproved(user.isApproved());

            return dto;
        }).toList();
    }

    // APPROVE USER (Admin action)
    public User approveUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + id));

        user.setApproved(true);

        return userRepository.save(user);
    }
    public AuthResponse loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isApproved()) {
            throw new RuntimeException("User not approved by admin");
        }

        String token = JWTutil.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token, user.getRole(), user.getEmail());
    }
}