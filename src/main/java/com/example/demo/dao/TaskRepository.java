package com.example.demo.dao;

import com.example.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task,Long> {
    List<Task> findAll();
    List<Task> findByUserId(long id);

    Task findById(long id);
}
