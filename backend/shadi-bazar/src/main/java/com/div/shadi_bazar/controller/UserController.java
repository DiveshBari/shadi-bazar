package com.div.shadi_bazar.controller;

import org.springframework.web.bind.annotation.RestController;

import com.div.shadi_bazar.Utils.StringUtils;
import com.div.shadi_bazar.dto.UserLoginDTO;
import com.div.shadi_bazar.dto.UserRegistrationDTO;
import com.div.shadi_bazar.dto.UserUpdateProfileDTO;
import com.div.shadi_bazar.entity.User;
import com.div.shadi_bazar.service.UserService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
        String credentials = StringUtils.getUserNameAndPasswordFromBase64(token);

        // Split the credentials into email and password
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];

        User user = userService.loginUser(email, password);

        if(user != null)
    		return ResponseEntity.ok().body("Login successful");
    	else
    		return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
    }

    /*
     * This endpoint allows a user to register by sending their details in the request body.
     * The details should be in JSON format and match the UserRegistrationDTO structure.
     */ 
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO dto) {
        User user = userService.registerUser(dto);
        if (user != null) {
            return ResponseEntity.ok().body("User registered successfully");
        } 
        else {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body("User registration failed");
        }
    }

    /*
     * Update user profile
     *
     * This endpoint allows a user to update their profile information.
     * The updated details should be sent in the request body.
     * The email parameter specifies which user's profile will be updated.
     */
    @PutMapping("/update-user")
    public String updateUser(@RequestHeader(value = "Authorization") String token, @RequestBody UserUpdateProfileDTO dto) {        
        String credentials = StringUtils.getUserNameAndPasswordFromBase64(token);
        
        // Split the credentials into email and password
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        
        // Validate the user's credentials before updating the profile
        User user = userService.loginUser(email, password);
        if (user == null) {
            return "Invalid credentials";
        }

        return userService.updateUserProfile(user, dto);
    }
    
    /*
     * This method handles any exceptions that occur during processing.
     * It returns an HTTP 500 Internal Server Error response with the exception message as the body.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
