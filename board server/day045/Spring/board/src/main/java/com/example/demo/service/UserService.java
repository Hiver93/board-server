package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserMapper;

@Service
public class UserService {

	private UserMapper userMapper;

	public UserService(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}
	
	public void createUser(User user) {
		this.userMapper.findByUsername(user.getUsername()).ifPresent((u)->{throw new BoardException(ErrorCode.USERNAME_CONFLICT);});
		this.userMapper.save(user);
	}
	
	public User login(User user) {
		return this.userMapper.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(()->{throw new BoardException(ErrorCode.INVALID_CREDENTIALS);});
	}
	
	public User getUser(Integer userId) {
		return this.userMapper.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
	}
}
