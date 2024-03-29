package com.fse.projectmanager.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanager.user.dto.UserDTO;
import com.fse.projectmanager.user.entity.User;
import com.fse.projectmanager.user.repository.UserRepository;
import com.fse.projectmanager.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
    private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElse(null);
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User getUser(UserDTO userDto) {
        User user;
        Optional<User> optionalUser;
        if (userDto.getUserId() != null && (optionalUser = userRepository.findById(userDto.getUserId())).isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
        }
        user.setUserId(userDto.getUserId());
        user.setEmployeeId(userDto.getEmployeeId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserActive(userDto.isActive());

        return user;
	}

	@Override
	public UserDTO getUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getUserId());
        userDto.setEmployeeId(user.getEmployeeId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setActive(user.isUserActive());
        return userDto;
	}

}
