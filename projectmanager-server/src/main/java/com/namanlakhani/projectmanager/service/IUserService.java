package com.namanlakhani.projectmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.namanlakhani.projectmanager.dto.UserDTO;
import com.namanlakhani.projectmanager.entity.User;

@Service
public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(UserDTO userDto);
    UserDTO getUserDto(User user);
}
