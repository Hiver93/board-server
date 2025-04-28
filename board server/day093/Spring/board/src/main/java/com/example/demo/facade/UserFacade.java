package com.example.demo.facade;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto.Profile;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFacade {

	private final UserService userService;
	private final AuthService authService;
	
	public void signup(UserReqDto.Signup dto) {
		this.userService.createUser(dto.getUsername(), dto.getPassword(), dto.getNickname());
	}
	
	public void login(UserReqDto.Login dto) {
		this.authService.setAuthentication(this.userService.getUser(dto.getUsername(), dto.getPassword()));
	}
	
	public Profile getProfile(Integer userId) {
		return Profile.from(this.userService.getUser(userId));
	}
	
	public void modifyUser(Integer userId, UserReqDto.Put dto) {
		this.userService.modifyUser(userId, dto.getNickname(), this.authService.authenticate());
	}
	
	public void removeUser(Integer userId, UserReqDto.Delete dto) {
		this.userService.removeUser(userId, dto.getPassword(), this.authService.authenticate());
	}
	
	public void logout() {
		this.authService.removeAuthentication();
	}
}
