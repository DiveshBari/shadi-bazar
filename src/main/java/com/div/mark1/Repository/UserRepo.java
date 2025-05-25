package com.div.mark1.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.div.mark1.Entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
}
