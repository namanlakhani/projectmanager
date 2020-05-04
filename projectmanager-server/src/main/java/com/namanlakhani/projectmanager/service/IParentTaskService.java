package com.namanlakhani.projectmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.namanlakhani.projectmanager.dto.ParentTaskDTO;
import com.namanlakhani.projectmanager.entity.ParentTask;

@Service
public interface IParentTaskService {

    List<ParentTask> getAllParentTasks();
    ParentTask getParentTaskById(Long parentId);
    void addParentTask(ParentTask parentTask);
    ParentTaskDTO getParentTaskDTO(ParentTask parentTask);
    ParentTask getParentTask(ParentTaskDTO parentTaskDto);
}
