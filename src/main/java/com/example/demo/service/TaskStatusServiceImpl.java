package com.example.demo.service;

import com.example.demo.dao.TaskStatusRepository;
import com.example.demo.models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusServiceImpl implements TaskStatusService{

    private TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskStatus> findAll() {
        return taskStatusRepository.findAll();
    }
}
