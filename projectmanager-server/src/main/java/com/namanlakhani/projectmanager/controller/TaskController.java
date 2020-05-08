package com.namanlakhani.projectmanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanlakhani.projectmanager.dto.TaskDTO;
import com.namanlakhani.projectmanager.entity.Task;
import com.namanlakhani.projectmanager.service.ITaskService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/projectmanager/tasks")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	/*
	 * public TaskController(ITaskService taskService) { this.taskService =
	 * taskService; }
	 */

	@GetMapping(path="/")
	public ResponseEntity<Object> getAllTasks(){
		List<Task> taskList = taskService.getAllTasks();
		List<TaskDTO> taskDtoList = taskList.stream().map(task -> taskService.getTaskDTO(task)).collect(Collectors.toList());
		return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
	}
	@PostMapping(path="/projecttask")
	public ResponseEntity<Object> getTaskByProject(@RequestBody TaskDTO taskDto){
		List<Task> taskList = taskService.getTasksByProjectId(taskDto.getProjectId());
		List<TaskDTO> taskDtoList = taskList.stream().map(task -> taskService.getTaskDTO(task)).collect(Collectors.toList());
		return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
	}
	
	@PostMapping(path="/add")
	public ResponseEntity<Object> addTask(@RequestBody TaskDTO taskDto) {
		Task task = taskService.getTask(taskDto);
		taskService.addTask(task);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path="/update")
	public ResponseEntity<Object> updateTask(@RequestBody TaskDTO taskDto){
		Task task = taskService.getTask(taskDto);
		taskService.updateTask(task);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path="/end")
	public ResponseEntity<Object> endTask(@RequestBody TaskDTO taskDto){
		Task task = taskService.getTask(taskDto);
		taskService.endTask(task);
		return new ResponseEntity<>(HttpStatus.OK);
}
	
	
}
