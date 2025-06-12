package com.div.shadi_bazar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private int id;
    private String name;
    private String email;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "phone")
    private String phoneNumber;
}
