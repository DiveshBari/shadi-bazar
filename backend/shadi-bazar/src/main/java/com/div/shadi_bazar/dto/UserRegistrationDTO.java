package com.div.shadi_bazar.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String name;
    private String email;  
    private String password; 
    private String phoneNumber;
}
