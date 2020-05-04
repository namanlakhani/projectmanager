package com.namanlakhani.projectmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.namanlakhani.projectmanager.dto.TaskDTO;
import com.namanlakhani.projectmanager.entity.Task;

@Service
public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long taskId);
    void addTask(Task task);
    void updateTask(Task task);
    void endTask(Task task);
    List<Task> getTasksByProjectId(Long projectId);
    Task getTask(TaskDTO taskDto);
    TaskDTO getTaskDTO(Task task);
}
