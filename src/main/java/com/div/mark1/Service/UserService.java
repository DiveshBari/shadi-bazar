package com.div.mark1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.div.mark1.Entity.Users;
import com.div.mark1.Repository.UserRepo;

@Service
public class UserService{

    private final UserRepo repo;

    @Autowired
    public UserService(UserRepo repo){
        this.repo = repo;
    }

    //Get all users
    public List<Users> getAllUsers(){
        List<Users> lsUsers = repo.findAll();
        return lsUsers;
    }
    
}
