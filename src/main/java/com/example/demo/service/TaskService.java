package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskService{
    void save (TaskDto taskDto);
    void update(TaskDto taskDto);
}
