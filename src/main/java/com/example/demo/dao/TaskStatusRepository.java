package com.example.demo.dao;

import com.example.demo.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long> {
    TaskStatus getByName(String name);
}
