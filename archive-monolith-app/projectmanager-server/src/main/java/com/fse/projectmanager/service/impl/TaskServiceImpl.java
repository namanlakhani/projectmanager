package com.fse.projectmanager.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanager.dto.TaskDTO;
import com.fse.projectmanager.entity.Task;
import com.fse.projectmanager.repository.TaskRepository;
import com.fse.projectmanager.service.IParentTaskService;
import com.fse.projectmanager.service.IProjectService;
import com.fse.projectmanager.service.ITaskService;
import com.fse.projectmanager.service.IUserService;

@Service
public class TaskServiceImpl implements ITaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private IParentTaskService parentTaskService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IUserService userService;

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task getTaskById(Long taskId) {
		Optional<Task> task = taskRepository.findById(taskId);
		return task.orElse(null);
	}

	@Override
	public void addTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void endTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public List<Task> getTasksByProjectId(Long projectId) {
		return taskRepository.findAll().stream().filter(task -> task.getProject().getProjectId().equals(projectId))
				.collect(Collectors.toList());
	}

	@Override
	public Task getTask(TaskDTO taskDto) {
		Task task;
		Optional<Task> optionalTask;
		if (taskDto.getTaskId() != null && (optionalTask = taskRepository.findById(taskDto.getTaskId())).isPresent()) {
			task = optionalTask.get();
		} else {
			task = new Task();
		}
		task.setStatus(taskDto.isStatus());
		task.setEndDate(taskDto.getEndDate());
		task.setParentTask(parentTaskService.getParentTaskById(taskDto.getParentId()));
		task.setPriority(taskDto.getPriority());
		task.setStartDate(taskDto.getStartDate());
		task.setTask(taskDto.getTask());
		task.setTaskId(taskDto.getTaskId());
		task.setUser(userService.getUserById(taskDto.getUserId()));
		task.setProject(projectService.getProjectById(taskDto.getProjectId()));
		return task;
	}

	@Override
	public TaskDTO getTaskDTO(Task task) {
		TaskDTO taskDto = new TaskDTO();
		taskDto.setStatus(task.isStatus());
		taskDto.setEndDate(task.getEndDate());
		taskDto.setParentId(task.getParentTask().getParentId());
		taskDto.setPriority(task.getPriority());
		taskDto.setStartDate(task.getStartDate());
		taskDto.setTask(task.getTask());
		taskDto.setTaskId(task.getTaskId());
		taskDto.setParentTask(task.getParentTask().getParentTask());
		taskDto.setProjectId(task.getProject().getProjectId());
		taskDto.setProject(task.getProject().getProject());
		taskDto.setUserId(task.getUser().getUserId());
		taskDto.setUserEmployeeId(task.getUser().getEmployeeId());
		taskDto.setUserName(task.getUser().getFirstName().concat(",").concat(task.getUser().getLastName()));
		return taskDto;
	}

}
