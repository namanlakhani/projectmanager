package com.namanlakhani.projectmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.namanlakhani.projectmanager.dto.UserDTO;
import com.namanlakhani.projectmanager.entity.User;
import com.namanlakhani.projectmanager.repository.UserRepository;
import com.namanlakhani.projectmanager.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
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
