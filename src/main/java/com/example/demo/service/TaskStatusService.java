package com.example.demo.service;

import com.example.demo.models.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> findAll();
}
