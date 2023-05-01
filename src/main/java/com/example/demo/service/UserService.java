package com.example.demo.service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.models.User;

public interface UserService {
    void save(RegisterDto registerDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
