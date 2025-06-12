package com.div.shadi_bazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.div.shadi_bazar.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Method to find a user by email
    User findByEmail(String email);
    
    // Method to find a user by phone number
    User findByPhoneNumber(String phoneNumber);
    
}
