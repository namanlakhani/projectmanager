package com.fse.projectmanager.controller;

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

import com.fse.projectmanager.dto.ProjectDTO;
import com.fse.projectmanager.entity.Project;
import com.fse.projectmanager.service.IProjectService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanager/projects")
public class ProjectController {
	
	@Autowired
	private IProjectService projectService;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllProjects() {
		return new ResponseEntity<>(projectService.getAllProjects().stream()
				.map(project -> projectService.getProjectDTO(project)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addProject(@RequestBody ProjectDTO projectDto) {
		Project project = projectService.getProject(projectDto);
		projectService.addProject(project);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateProject(@RequestBody ProjectDTO projectDto) {
		Project project = projectService.getProject(projectDto);
		projectService.updateProject(project);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/suspend")
	public ResponseEntity<Object> suspendProject(@RequestBody ProjectDTO projectDto) {
		Project project = projectService.getProject(projectDto);
		projectService.suspendProject(project);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
