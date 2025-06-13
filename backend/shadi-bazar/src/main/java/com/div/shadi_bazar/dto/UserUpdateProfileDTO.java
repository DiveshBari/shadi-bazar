package com.div.shadi_bazar.dto;

import lombok.Data;

@Data
public class UserUpdateProfileDTO {
    private String name;
    private String password; 
    private String phoneNumber;
}
