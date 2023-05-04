package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.RegisterDto;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(RegisterDto registerDto) {
        userRepository.save(User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .password(registerDto.getPassword())
                .role(roleRepository.findByName("STANDART_USER")).build());
    }

    @Override
    public User saveAndFlush(RegisterDto registerDto) {
        return userRepository.saveAndFlush(User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .password(registerDto.getPassword())
                .role(roleRepository.findByName("STANDART_USER")).build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
