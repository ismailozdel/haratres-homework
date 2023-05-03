package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;

import java.util.List;

public interface TaskService{
    void save (TaskDto taskDto);
    void update(TaskDto taskDto);

    void delete(long id);
    List<Task> findAll();

    List<Task> findByUserId(long id);

    Task findById(long id);
}
