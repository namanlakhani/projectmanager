package com.fse.projectmanager.user.service;

import java.util.List;

import com.fse.projectmanager.user.dto.UserDTO;
import com.fse.projectmanager.user.entity.User;


public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(UserDTO userDto);
    UserDTO getUserDto(User user);
}
