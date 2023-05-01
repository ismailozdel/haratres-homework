package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        RegisterDto user = new RegisterDto();
        model.addAttribute("user",user);
        model.addAttribute("message","asd");
        return "register-page";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")RegisterDto user,Model model){
        User existingEmail = userService.findByEmail(user.getEmail());
        if(existingEmail != null && user.getEmail() != null){
            return "This email already used";
        }
        User existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && user.getUsername() != null){
            return "This username already used";
        }
        userService.save(user);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        LoginDto user = new LoginDto();
        model.addAttribute("user",user);
        return "login-page";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user")LoginDto user,Model model){
        User userQuery = userService.findByUsername(user.getUsername());
        if(user.getUsername() != null && userQuery !=null && userQuery.getPassword().equals(user.getPassword())){
            System.out.println("yeyyy");
        }
        else System.out.println("nayy");
        return "redirect:/";
    }
}
