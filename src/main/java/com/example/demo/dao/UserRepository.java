package com.example.demo.dao;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String email);

    User findById(long id);
}
