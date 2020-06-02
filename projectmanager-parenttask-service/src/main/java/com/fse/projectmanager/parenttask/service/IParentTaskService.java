package com.fse.projectmanager.parenttask.service;

import java.util.List;

import com.fse.projectmanager.parenttask.dto.ParentTaskDTO;
import com.fse.projectmanager.parenttask.entity.ParentTask;


public interface IParentTaskService {

    List<ParentTask> getAllParentTasks();
    ParentTask getParentTaskById(Long parentId);
    void addParentTask(ParentTask parentTask);
    ParentTaskDTO getParentTaskDTO(ParentTask parentTask);
    ParentTask getParentTask(ParentTaskDTO parentTaskDto);
}
