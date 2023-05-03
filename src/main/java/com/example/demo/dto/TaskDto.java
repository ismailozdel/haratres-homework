package com.example.demo.dto;

import com.example.demo.models.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class TaskDto {
    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    private String summary;
    private long status;
    private TaskStatus taskStatus;
}
