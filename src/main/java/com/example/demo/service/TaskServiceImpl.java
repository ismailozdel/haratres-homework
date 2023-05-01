package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.TaskStatusRepository;
import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import com.example.demo.models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public void save(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setSummary(taskDto.getSummary());
        task.setDueDate(taskDto.getDueData());
        TaskStatus taskStatus = taskStatusRepository.getByName(taskDto.getTaskStatus().getName());
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDto taskDto) {

    }
}
