package com.example.demo.dto;

import com.example.demo.models.TaskStatus;
import lombok.Data;

import java.util.Date;
@Data
public class TaskDto {
    private long id;
    private String name;
    private Date dueData;
    private String summary;
    private long status;
    private TaskStatus taskStatus;
}
