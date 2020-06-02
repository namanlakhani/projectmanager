package com.fse.projectmanager.user.controller;

import java.util.List;
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

import com.fse.projectmanager.user.dto.UserDTO;
import com.fse.projectmanager.user.entity.User;
import com.fse.projectmanager.user.service.IUserService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/projectmanager/users")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * Method for getting all Users
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		List<UserDTO> userListDto = userList.stream().map(activeUser -> userService.getUserDto(activeUser))
				.collect(Collectors.toList());
		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}

	/**
	 * Method for getting all Users
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	/**
	 * Method for Adding User
	 * 
	 * @param userDto
	 * @return
	 */

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addUser(@RequestBody UserDTO userDto) {
		User user = userService.getUser(userDto);
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method for Updating User
	 * 
	 * @param userDto
	 * @return
	 */
	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDto) {
		User user = userService.getUser(userDto);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Method for Deleting User
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping(path = "/delete")
	public ResponseEntity<Object> deleteUser(@RequestBody UserDTO userDto) {
		User user = userService.getUser(userDto);
		userService.deleteUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
