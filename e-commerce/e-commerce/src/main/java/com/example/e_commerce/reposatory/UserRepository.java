package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public  User findByEmail(String email);
}
