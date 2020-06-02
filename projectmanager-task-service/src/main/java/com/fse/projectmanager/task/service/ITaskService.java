package com.fse.projectmanager.task.service;

import java.util.List;

import com.fse.projectmanager.task.dto.TaskDTO;
import com.fse.projectmanager.task.entity.ParentTask;
import com.fse.projectmanager.task.entity.Project;
import com.fse.projectmanager.task.entity.Task;
import com.fse.projectmanager.task.entity.User;


public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long taskId);
    void addTask(Task task);
    void updateTask(Task task);
    void endTask(Task task);
    List<Task> getTasksByProjectId(Long projectId);
    Task getTask(TaskDTO taskDto,User user,Project project,ParentTask parenttask);
    TaskDTO getTaskDTO(Task task);
}
