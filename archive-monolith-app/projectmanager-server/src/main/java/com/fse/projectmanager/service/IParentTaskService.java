package com.fse.projectmanager.service;

import java.util.List;

import com.fse.projectmanager.dto.ParentTaskDTO;
import com.fse.projectmanager.entity.ParentTask;


public interface IParentTaskService {

    List<ParentTask> getAllParentTasks();
    ParentTask getParentTaskById(Long parentId);
    void addParentTask(ParentTask parentTask);
    ParentTaskDTO getParentTaskDTO(ParentTask parentTask);
    ParentTask getParentTask(ParentTaskDTO parentTaskDto);
}
