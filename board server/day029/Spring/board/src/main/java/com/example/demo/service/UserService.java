package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.error.CustomException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public User getUesr(User user) {
		User saved = this.userRepository.findByUsername(user.getUsername()).orElseThrow(()->{throw new CustomException(ErrorCode.INVALID_CREDENTIALS);});
		if(!saved.getPassword().equals(user.getPassword())) {
			throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
		}
		return saved;
	}
	
	public User getUser(Integer userId) {
		User saved = this.userRepository.findById(userId).orElseThrow();
		return saved;
	}
	
	public void createUser(User user) {
		if(this.userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new CustomException(ErrorCode.USERNAME_CONFLICT);
		}
		this.userRepository.save(user);
	}
}
