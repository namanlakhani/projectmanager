package com.fse.projectmanager.project.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fse.projectmanager.project.dto.ProjectDTO;
import com.fse.projectmanager.project.entity.Project;
import com.fse.projectmanager.project.entity.User;
import com.fse.projectmanager.project.service.IProjectService;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/projectmanager/projects")
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllProjects() {
		return new ResponseEntity<>(projectService.getAllProjects().stream()
				.map(project -> projectService.getProjectDTO(project)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProject(@PathVariable("id") Long id) {
		Project project = projectService.getProjectById(id);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}


	@PostMapping(path = "/add")
	public ResponseEntity<Object> addProject(@RequestBody ProjectDTO projectDto) {
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + projectDto.getManagerId(),
				User.class);
		Project project = projectService.getProject(projectDto, user);
		projectService.addProject(project);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateProject(@RequestBody ProjectDTO projectDto) {
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + projectDto.getManagerId(),
				User.class);
		Project project = projectService.getProject(projectDto, user);
		projectService.updateProject(project);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/suspend")
	public ResponseEntity<Object> suspendProject(@RequestBody ProjectDTO projectDto) {
		User user = restTemplate.getForObject("http://user-service/projectmanager/users/" + projectDto.getManagerId(),
				User.class);
		Project project = projectService.getProject(projectDto, user);
		projectService.suspendProject(project);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
