package com.div.shadi_bazar.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.div.shadi_bazar.dto.UserLoginDTO;
import com.div.shadi_bazar.dto.UserRegistrationDTO;
import com.div.shadi_bazar.dto.UserUpdateProfileDTO;
import com.div.shadi_bazar.entity.User;
import com.div.shadi_bazar.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Check if the list is empty
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        // Sort users by name
        Comparator<User> nameComparator = (User u1, User u2) -> u1.getName().compareTo(u2.getName());
        List<User> sortByName = users.stream().sorted(nameComparator).toList();

        return users;
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Return the user if login is successful
        } else {
            return null; // Return null if login fails
        }
    }

    /*
     * Register a new user based on the provided registration data.
     *
     * @param dto The DTO containing user registration information.
     * @return The registered user entity.
     */
    public User registerUser(UserRegistrationDTO dto) {
        // Create a new user entity and save it
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());

        user = userRepository.save(user);
        return user;
    }

    /*
     * Update an existing user's profile based on the provided update data.
     *
     * @param entity The DTO containing updated user information.
     * @param email  The email address of the user to be updated.
     * @return A message indicating whether the update was successful or not.
     */
    public String updateUserProfile(User existingUser, UserUpdateProfileDTO dto) {
        // Check if the user exists by email
        if (existingUser != null) {
            // Update the user's details
            User user = new User();
            user.setId(existingUser.getId());
            user.setEmail(existingUser.getEmail());
            user.setName(dto.getName());
            user.setPassword(dto.getPassword());
            user.setPhoneNumber(dto.getPhoneNumber());
            user = userRepository.save(user);

            return "User updated successfully";
        }
        else{
            throw new RuntimeException("User does not exist");
        }
    }
}
