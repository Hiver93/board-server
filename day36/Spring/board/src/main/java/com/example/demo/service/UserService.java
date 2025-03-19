package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	public void createUser(User user) {
		this.userRepository.findByUsername(user.getUsername()).ifPresent((u)->{throw new BoardException(ErrorCode.USERNAME_CONFLICT);});
		this.userRepository.save(user);
	}
	
	public User getUser(User user) {
		return this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(()->{throw new BoardException(ErrorCode.INVALED_CREDENTIALS);});
	}
	
	@Transactional(readOnly = true)
	public User getUser(Integer userId) {
		return this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
	}
	
}
