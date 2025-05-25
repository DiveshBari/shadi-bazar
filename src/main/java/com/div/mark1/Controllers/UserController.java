package com.div.mark1.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.div.mark1.Entity.Users;
import com.div.mark1.Repository.UserRepo;
import com.div.mark1.Service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    
    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Users> getAllUsers() {
        System.out.println("Inside getAllUsers");

        // Use userService to get the users
        return service.getAllUsers();
    }
    
}
