package com.namanlakhani.projectmanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanlakhani.projectmanager.dto.UserDTO;
import com.namanlakhani.projectmanager.entity.User;
import com.namanlakhani.projectmanager.service.IUserService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/projectmanager/users")
public class UserController {
	
	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping(path="/")
	public ResponseEntity<Object> getAllUsers(){
		List<User> userList = userService.getAllUsers();
		List<UserDTO> userListDto = userList.stream().map(activeUser -> userService.getUserDto(activeUser)).collect(Collectors.toList());
		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}

	@PostMapping(path="/add")
	public ResponseEntity<Object> addUser(@RequestBody UserDTO userDto) {
		User user = userService.getUser(userDto);
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(path="/update")
	public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDto){
		User user = userService.getUser(userDto);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path="/delete")
	public ResponseEntity<Object> deleteUser(@RequestBody UserDTO userDto){
		User user = userService.getUser(userDto);
		userService.deleteUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
}
	

}
