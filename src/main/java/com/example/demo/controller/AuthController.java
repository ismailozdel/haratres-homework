package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static com.example.demo.utils.ControllerUtils.*;


@Controller
public class AuthController {
    final private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/")
    public String index(HttpServletRequest request){
        if(isAuthenticated(request,userService)) return "index";
        else return "redirect:/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model,HttpServletRequest request){
        if(isAuthenticated(request,userService)) return "redirect:/";
        RegisterDto user = new RegisterDto();
        model.addAttribute("user",user);
        model.addAttribute("message","asd");
        return "register-page";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")RegisterDto user,HttpServletResponse response,HttpServletRequest request){
        if(isAuthenticated(request,userService)) return "redirect:/";
        User existingEmail = userService.findByEmail(user.getEmail());
        if(existingEmail != null && user.getEmail() != null){
            return "redirect:/register";
        }
        User existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && user.getUsername() != null){
            return "redirect:/register";
        }
        User user1 = userService.saveAndFlush(user);
        Cookie cookie = new Cookie("user_id",Long.toString(user1.getId()));
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model, HttpServletRequest request){
        if(isAuthenticated(request,userService)) return "redirect:/";
        LoginDto user = new LoginDto();
        model.addAttribute("user",user);
        return "login-page";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user")LoginDto user, HttpServletResponse response,HttpServletRequest request){
        if(isAuthenticated(request,userService)) return "redirect:/";
        User userQuery = userService.findByUsername(user.getUsername());
        if(user.getUsername() != null && userQuery !=null && userQuery.getPassword().equals(user.getPassword())){
            Cookie cookie = new Cookie("user_id",Long.toString(userQuery.getId()));
            response.addCookie(cookie);
            if(userQuery.getRole().getName().equals("ADMIN")) return "redirect:/admin";
            return "redirect:/";
        }
        return "login-page";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("user_id",null);
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
