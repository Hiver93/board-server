package com.example.demo.facade;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto.Profile;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;

@Service
public class UserFacade {

	private UserService userService;
	private AuthService authService;
	public UserFacade(UserService userService, AuthService authService) {
		super();
		this.userService = userService;
		this.authService = authService;
	}
	
	public void signup(UserReqDto.Signup dto) {
		this.userService.createUser(dto.getUsername(), dto.getPassword(), dto.getNickname());
	}
	
	public void login(UserReqDto.Login dto) {
		this.authService.setAuthentication(this.userService.getUser(dto.getUsername(), dto.getPassword()));
	}
	
	public Profile getProfile(Integer userId) {
		return Profile.from(this.userService.getUser(userId));
	}
	
	public void removeUser(Integer userId, UserReqDto.Delete dto) {
		this.userService.removeUser(userId, dto.getPassword(), this.authService.authenticate());
	}
	
	public void modifyUser(Integer userId, UserReqDto.Put dto) {
		this.userService.modifyUser(userId, dto.getNickname(), this.authService.authenticate());
	}
}
