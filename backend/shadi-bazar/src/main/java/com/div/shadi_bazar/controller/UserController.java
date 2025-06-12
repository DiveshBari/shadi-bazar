package com.div.shadi_bazar.controller;

import org.springframework.web.bind.annotation.RestController;

import com.div.shadi_bazar.dto.UserLoginDTO;
import com.div.shadi_bazar.dto.UserRegistrationDTO;
import com.div.shadi_bazar.entity.User;
import com.div.shadi_bazar.service.UserService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/all-users")
    public List<User> getAllUsers() throws RuntimeException {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    /*
     * This endpoint allows a user to log in using their email and password.
     * The email and password should be sent in the Authorization header
     */
    public ResponseEntity<String> loginUser(@RequestHeader(value="Authorization") String token) {
    	//token in the format "Basic base64(email:password)"
        String base64Credentials = token.substring("Basic ".length()).trim();
        // Decode the base64 encoded
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodedBytes);
        
        // Split the credentials into email and password
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];

        User user = userService.loginUser(email, password);

        if(user != null)
    		return ResponseEntity.ok().body("Login successful");
    	else
    		return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
    }
}
