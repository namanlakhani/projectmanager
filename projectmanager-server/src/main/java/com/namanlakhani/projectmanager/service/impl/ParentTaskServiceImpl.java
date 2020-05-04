package com.namanlakhani.projectmanager.service.impl;

import java.util.List;
import java.util.Optional;

import com.namanlakhani.projectmanager.dto.ParentTaskDTO;
import com.namanlakhani.projectmanager.entity.ParentTask;
import com.namanlakhani.projectmanager.repository.ParentTaskRepository;
import com.namanlakhani.projectmanager.service.IParentTaskService;

public class ParentTaskServiceImpl implements IParentTaskService{

	private final ParentTaskRepository parentTaskRepository;

	public ParentTaskServiceImpl(ParentTaskRepository parentTaskRepository) {
	        this.parentTaskRepository = parentTaskRepository;
	}
	
	@Override
	public List<ParentTask> getAllParentTasks() {
		 return parentTaskRepository.findAll();
	}

	@Override
	public ParentTask getParentTaskById(Long parentId) {
        Optional<ParentTask> parentTask = parentTaskRepository.findById(parentId);
        return parentTask.orElse(null);
	}

	@Override
	public void addParentTask(ParentTask parentTask) {
		 parentTaskRepository.save(parentTask);		
	}

	@Override
	public ParentTaskDTO getParentTaskDTO(ParentTask parentTask) {
        ParentTaskDTO parentTaskDto = new ParentTaskDTO();
        parentTaskDto.setParentId(parentTask.getParentId());
        parentTaskDto.setParentTask(parentTask.getParentTask());
        return parentTaskDto;
	}

	@Override
	public ParentTask getParentTask(ParentTaskDTO parentTaskDto) {
        ParentTask parentTask;
        Optional<ParentTask> optionalParentTask;
        if (parentTaskDto.getParentId() != null && (optionalParentTask = parentTaskRepository.findById(parentTaskDto.getParentId())).isPresent()) {
            parentTask = optionalParentTask.get();
        } else {
            parentTask = new ParentTask();
        }
        parentTask.setParentId(parentTaskDto.getParentId());
        parentTask.setParentTask(parentTaskDto.getParentTask());

        return parentTask;
	}

}
