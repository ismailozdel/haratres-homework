package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import com.example.demo.service.TaskService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTaskPage(Model model){
        model.addAttribute("tasks",taskService.findAll());
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task",taskDto);
        return "task-list";
    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute("task")TaskDto task, HttpServletRequest request){
        List<Cookie> cookies = Arrays.stream(request.getCookies()).toList();
        for (Cookie cookie:cookies
             ) {
            if(cookie.getName().equals("user_id")){
                System.out.println(cookie.getValue());
            }
        }

        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable long id, Model model){
        model.addAttribute("task",taskService.findById(id));
        return "task-page";
    }

    @PostMapping("/task/{id}/update")
    public String updateTask(@PathVariable long id,@ModelAttribute("task")TaskDto task,Model model){
        task.setId(id);
        taskService.update(task);
        return "redirect:/tasks";
    }
    @GetMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable long id){
        taskService.delete(id);
        return "redirect:/tasks";
    }
}
