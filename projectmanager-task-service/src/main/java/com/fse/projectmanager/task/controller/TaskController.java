package com.fse.projectmanager.task.controller;

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
import org.springframework.web.client.RestTemplate;

import com.fse.projectmanager.task.dto.TaskDTO;
import com.fse.projectmanager.task.entity.ParentTask;
import com.fse.projectmanager.task.entity.Project;
import com.fse.projectmanager.task.entity.Task;
import com.fse.projectmanager.task.entity.User;
import com.fse.projectmanager.task.service.ITaskService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/projectmanager/tasks")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@Autowired
	private RestTemplate restTemplate;

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
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + taskDto.getUserEmployeeId(),
				User.class);
		Project project = restTemplate.getForObject("http://project-service/projectmanager/projects/" + taskDto.getProjectId(),
				Project.class);
		ParentTask parenttask = restTemplate.getForObject("http://parenttask-service/projectmanager/parenttasks/" + taskDto.getParentId(),
				ParentTask.class);
		Task task = taskService.getTask(taskDto,user,project,parenttask);
		taskService.addTask(task);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path="/update")
	public ResponseEntity<Object> updateTask(@RequestBody TaskDTO taskDto){
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + taskDto.getUserEmployeeId(),
				User.class);
		Project project = restTemplate.getForObject("http://project-service/projectmanager/projects/" + taskDto.getProjectId(),
				Project.class);
		ParentTask parenttask = restTemplate.getForObject("http://parenttask-service/projectmanager/parenttasks/" + taskDto.getParentId(),
				ParentTask.class);
		Task task = taskService.getTask(taskDto,user,project,parenttask);
		taskService.updateTask(task);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path="/end")
	public ResponseEntity<Object> endTask(@RequestBody TaskDTO taskDto){
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + taskDto.getUserEmployeeId(),
				User.class);
		Project project = restTemplate.getForObject("http://project-service/projectmanager/projects/" + taskDto.getProjectId(),
				Project.class);
		ParentTask parenttask = restTemplate.getForObject("http://parenttask-service/projectmanager/parenttasks/" + taskDto.getParentId(),
				ParentTask.class);
		Task task = taskService.getTask(taskDto,user,project,parenttask);		taskService.endTask(task);
		return new ResponseEntity<>(HttpStatus.OK);
}
	
	
}
