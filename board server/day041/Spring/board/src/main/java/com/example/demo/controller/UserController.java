package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.domain.User;
import com.example.demo.dto.UserReqDto;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private AuthenticationService authenticationService;
	public UserController(UserService userService, AuthenticationService authenticationService) {
		super();
		this.userService = userService;
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResBody> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.createUser(dto.to());
		return BaseResBody.of(HttpStatus.CREATED, "signed up");
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResBody> login(@RequestBody @Valid UserReqDto.Login dto){
		User result = this.userService.getUser(dto.to());
		authenticationService.setAuthentication(result);
		return BaseResBody.of(HttpStatus.OK, "loged in");
	}
	
	@PostMapping("/reissue")
	public ResponseEntity<BaseResBody> reissue(){
		authenticationService.refreshAuthentication();
		return BaseResBody.of(HttpStatus.OK, "refreshed");
	}
}
