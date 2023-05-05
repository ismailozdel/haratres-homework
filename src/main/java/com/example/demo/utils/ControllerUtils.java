package com.example.demo.utils;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ControllerUtils {


    public static boolean isAuthenticated(HttpServletRequest request, UserService userService){
        List<Cookie> cookies = Arrays.stream(request.getCookies()).toList();
        for (Cookie cookie:cookies
        ) {
            if(cookie.getName().equals("user_id")
                    && !cookie.getValue().isEmpty()
                    && userService.findById(Long.parseLong(cookie.getValue())) != null){
                return true;
            }
        }
        return false;
    }
    public static User getUserInCookies(HttpServletRequest request, UserService userService){
        List<Cookie> cookies = Arrays.stream(request.getCookies()).toList();
        for (Cookie cookie:cookies
        ) {
            if(cookie.getName().equals("user_id")
                    && !cookie.getValue().isEmpty()){
                User user = userService.findById(Long.parseLong(cookie.getValue()));
                return user;
            }
        }
        return null;
    }

}
