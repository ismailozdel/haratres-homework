package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
