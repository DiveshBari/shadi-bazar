package com.div.shadi_bazar.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.div.shadi_bazar.dto.UserLoginDTO;
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
}
