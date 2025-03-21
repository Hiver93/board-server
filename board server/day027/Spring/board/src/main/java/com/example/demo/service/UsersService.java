package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.error.ErrorCode;
import com.example.demo.error.UsersException;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersService {

	private UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}
	
	public void createUsers(Users users) {
		if(this.usersRepository.findByUsername(users.getUsername()).isPresent()) {
			throw new UsersException(ErrorCode.USERNAME_CONFLICT);
		}
		this.usersRepository.save(users);
	}
	
	public Users getUsers(Users users) {
		Users saved = this.usersRepository.findByUsername(users.getUsername()).orElseThrow(()->{throw new UsersException(ErrorCode.INVALID_CREDENTIALS);});
		if(!saved.getPassword().equals(users.getPassword())) {
			throw new UsersException(ErrorCode.INVALID_CREDENTIALS);
		}
		return saved;
	}
}
