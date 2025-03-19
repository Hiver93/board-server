package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private AuthService authService;
	public UserController(UserService userService, AuthService authService) {
		super();
		this.userService = userService;
		this.authService = authService;
	}
	
	@PostMapping("signup")
	public ResponseEntity<BaseResBody> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.createUser(dto.to());
		return BaseResBody.of(HttpStatus.CREATED, "signed");
	}
	@PostMapping("login")
	public ResponseEntity<BaseResBody> login(@RequestBody @Valid UserReqDto.Login dto){
		this.authService.setAuthentication(this.userService.login(dto.to()));
		return BaseResBody.of(HttpStatus.OK, "loged");
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<BaseResBody> getProfile(@PathVariable(name = "userId") Integer userId){
		var result = UserResDto.Profile.from(this.userService.getUser(userId));
		return BaseResBody.of(HttpStatus.OK, result, "get profile");
	}
}
