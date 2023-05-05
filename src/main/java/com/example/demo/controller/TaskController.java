package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskStatusService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static com.example.demo.utils.ControllerUtils.*;
import java.util.List;

@Controller
public class TaskController {

    final private TaskService taskService;
    final private UserService userService;

    final private TaskStatusService taskStatusService;

    public TaskController(TaskService taskService,UserService userService, TaskStatusService taskStatusService) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskStatusService = taskStatusService;
    }

    @GetMapping("/tasks")
    public String getTaskPage(Model model, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        User user = getUserInCookies(request,userService);
        model.addAttribute("tasks",taskService.findByUserId(user.getId()));
        model.addAttribute("taskStatuses",taskStatusService.findAll());
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task",taskDto);
        return "task-list";
    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute("task")TaskDto task, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        User user = getUserInCookies(request,userService);
        task.setUser(user);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable long id, Model model, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        User user = getUserInCookies(request,userService);
        Task task = taskService.findById(id);
        if(task.getUser().getId() == user.getId())
            model.addAttribute("task",task);
        else return "redirect:/tasks";
        return "task-page";
    }

    @PostMapping("/task/{id}/update")
    public String updateTask(@PathVariable long id,@ModelAttribute("task")TaskDto task,HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        User user = getUserInCookies(request,userService);
        task.setId(id);
        task.setUser(user);
        if(task.getUser().getId() == user.getId())
            taskService.update(task);
        return "redirect:/tasks";
    }
    @GetMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable long id, HttpServletRequest request){
        if(!isAuthenticated(request,userService)) return "redirect:/login";
        User user = getUserInCookies(request,userService);
        Task task = taskService.findById(id);
        if(task.getUser().getId() == user.getId())
            taskService.delete(task.getId());
        return "redirect:/tasks";
    }

    @GetMapping("/a")
    public void gett(){
        List<Task> tasks = userService.findById(8).getTasks();
        for (Task task:tasks
             ) {
            System.out.println(task.getName());
        }
    }
}
