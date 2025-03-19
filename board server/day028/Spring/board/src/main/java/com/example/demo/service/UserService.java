package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.error.ErrorCode;
import com.example.demo.error.UserException;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private AuthRepository authRepository;
	public UserService(UserRepository userRepository, AuthRepository authRepository) {
		super();
		this.userRepository = userRepository;
		this.authRepository = authRepository;
	}
	
	
	public void createUser(User user) {
		this.userRepository.save(user);
	}
	
	public String getUuid(User user) {
		User saved = this.userRepository.findByUsername(user.getUsername()).orElseThrow(()->{throw new UserException(ErrorCode.INVALID_CREDENTIALS);});
		if(!saved.getPassword().equals(user.getPassword())) {
			throw new UserException(ErrorCode.INVALID_CREDENTIALS);
		}
		String uuid = UUID.randomUUID().toString();
		this.authRepository.save(uuid, saved);
		return uuid;
	}
	
	public User getUser(String uuid) {
		User saved = this.authRepository.findByUuid(uuid).orElseThrow(()->{throw new UserException(ErrorCode.INVALID_UUID);});
		return saved;
	}
}
