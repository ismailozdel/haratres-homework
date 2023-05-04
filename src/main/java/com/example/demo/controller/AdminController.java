package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.demo.utils.ControllerUtils.getUserInCookies;
import static com.example.demo.utils.ControllerUtils.isAuthenticated;

@Controller
public class AdminController {

    private UserService userService;
    private TaskService taskService;

    public AdminController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    private boolean isAdmin(User user){
        if(user.getRole().getName().equals("ADMIN")) return true;
        else return false;
    }

    @GetMapping("/admin")
    public String indexAdmin(Model model, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        model.addAttribute("users",userService.findAll());
        return "admin-page";
    }

    @GetMapping("/admin/user_detail/{id}")
    public String getUserDetail(@PathVariable long id,Model model,HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        User user = userService.findById(id);
        TaskDto taskDto = new TaskDto();
        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("task", taskDto);
            model.addAttribute("tasks", taskService.findByUserId(user.getId()));
        }
        else
            return "redirect:/admin";
        return "admin-user-detail";
    }
    @PostMapping("/admin/user_detail/{id}/task/save")
    public String adminSaveUserTask(@PathVariable long id, @ModelAttribute("task") TaskDto taskDto, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        taskDto.setUser(userService.findById(id));
        taskService.save(taskDto);
        return "redirect:/admin/user_detail/{id}";
    }
    @GetMapping("/admin/user_detail/{id}/task/{task_id}")
    public String adminUserTaskPage(@PathVariable long id, @PathVariable long task_id, Model model, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        model.addAttribute("task",taskService.findById(task_id));
        model.addAttribute("user",userService.findById(id));
        return "admin-user-task-detail";
    }
    @PostMapping("/admin/user_detail/{id}/task/{task_id}/update")
    public String adminUpdateUserTask(@PathVariable long id, @PathVariable long task_id,@ModelAttribute("task") TaskDto taskDto, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        taskDto.setId(task_id);
        taskDto.setUser(userService.findById(id));
        taskService.update(taskDto);
        return "redirect:/admin/user_detail/{id}";
    }
    @GetMapping("/admin/user_detail/{id}/task/{task_id}/delete")
    public String adminDeleteUserTask(@PathVariable long id, @PathVariable long task_id, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        if(!isAdmin(getUserInCookies(request,userService))) return "redirect:/";
        Task task = taskService.findById(task_id);
        if(task.getUser().getId() == id && task != null)
            taskService.delete(task_id);
        return "redirect:/admin/user_detail/{id}";
    }

}
