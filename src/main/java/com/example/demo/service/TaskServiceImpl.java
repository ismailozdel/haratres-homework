package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.TaskStatusRepository;
import com.example.demo.dto.TaskDto;
import com.example.demo.models.Task;
import com.example.demo.models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        taskRepository.save(Task.builder().name(taskDto.getName())
                .summary(taskDto.getSummary())
                .dueDate(taskDto.getDueDate())
                .taskStatus(taskStatusRepository.getById(taskDto.getTaskStatus().getId()))
                .user(taskDto.getUser()).build());
    }

    @Override
    public void update(TaskDto taskDto) {
        Task task = taskRepository.findById(taskDto.getId());
        if(task !=null){
            Task task1 = Task.builder().id(taskDto.getId())
                    .name(taskDto.getName())
                    .summary(taskDto.getSummary())
                    .dueDate(taskDto.getDueDate())
                    .taskStatus(taskStatusRepository.getById(taskDto.getTaskStatus().getId()))
                    .user(taskDto.getUser()).build();
            taskRepository.save(task1);
        }
    }

    @Override
    public void delete(long id) {
        taskRepository.delete(taskRepository.getById(id));
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByUserId(long id) {
        return taskRepository.findByUserId(id);
    }

    @Override
    public Task findById(long id) {
        return taskRepository.findById(id);
    }
}
