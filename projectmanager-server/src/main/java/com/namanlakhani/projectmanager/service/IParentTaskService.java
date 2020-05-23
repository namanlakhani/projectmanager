package com.namanlakhani.projectmanager.service;

import java.util.List;

import com.namanlakhani.projectmanager.dto.ParentTaskDTO;
import com.namanlakhani.projectmanager.entity.ParentTask;


public interface IParentTaskService {

    List<ParentTask> getAllParentTasks();
    ParentTask getParentTaskById(Long parentId);
    void addParentTask(ParentTask parentTask);
    ParentTaskDTO getParentTaskDTO(ParentTask parentTask);
    ParentTask getParentTask(ParentTaskDTO parentTaskDto);
}
