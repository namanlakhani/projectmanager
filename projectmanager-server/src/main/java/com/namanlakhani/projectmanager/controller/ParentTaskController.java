package com.namanlakhani.projectmanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanlakhani.projectmanager.dto.ParentTaskDTO;
import com.namanlakhani.projectmanager.entity.ParentTask;
import com.namanlakhani.projectmanager.service.IParentTaskService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/projectmanager/parenttasks")
public class ParentTaskController {
    
	
	@Autowired
	private IParentTaskService parentTaskService;

	/*
	 * public ParentTaskController(IParentTaskService parentTaskService) {
	 * this.parentTaskService = parentTaskService; }
	 */

    @GetMapping(path="/")
    public ResponseEntity<Object> getAllParentTasks(){
        List<ParentTask> parentTaskList = parentTaskService.getAllParentTasks();
        List<ParentTaskDTO> parentTaskDtoList = parentTaskList.stream().map(parentTask -> parentTaskService.getParentTaskDTO(parentTask)).collect(Collectors.toList());
        return new ResponseEntity<>(parentTaskDtoList, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Object> addTask(@RequestBody ParentTaskDTO parentTaskDto) {
        ParentTask parentTask = parentTaskService.getParentTask(parentTaskDto);
        parentTaskService.addParentTask(parentTask);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
