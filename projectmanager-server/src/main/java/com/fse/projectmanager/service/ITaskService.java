package com.fse.projectmanager.service;

import java.util.List;

import com.fse.projectmanager.dto.TaskDTO;
import com.fse.projectmanager.entity.Task;


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
