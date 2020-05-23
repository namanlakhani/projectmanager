package com.namanlakhani.projectmanager.service;

import java.util.List;

import com.namanlakhani.projectmanager.dto.UserDTO;
import com.namanlakhani.projectmanager.entity.User;


public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(UserDTO userDto);
    UserDTO getUserDto(User user);
}
