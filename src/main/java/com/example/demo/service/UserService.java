package com.example.demo.service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(RegisterDto registerDto);

    User saveAndFlush(RegisterDto registerDto);

    List<User> findAll();

    User findById(long id);
    User findByEmail(String email);

    User findByUsername(String username);
}
