package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	private AuthService authService;
	private UserService userService;
	public UserController(AuthService authService, UserService userService) {
		super();
		this.authService = authService;
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResBody<Void>> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.CreateUser(dto.getUsername(), dto.getPassword(), dto.getNickname());
		return new BaseResBody<Void>(null, "signed")
				.toResponse(HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResBody<Void>> login(@RequestBody @Valid UserReqDto.Login dto){
		this.authService.setAuthentication(this.userService.getUser(dto.getUsername(),dto.getPassword()));
		return new BaseResBody<Void>(null, "loged in")
				.toResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<BaseResBody<UserResDto.Profile>> getProfile(@PathVariable(name = "userId") Integer userId){
		var result = UserResDto.Profile.from(this.userService.getUser(userId));
		return new BaseResBody<>(result, "get profile")
				.toResponse(HttpStatus.OK);
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<BaseResBody<Void>> patchUser(@PathVariable(name = "userId") Integer userId, @RequestBody @Valid UserReqDto.Patch dto){
		this.userService.modifyUser(userId, dto.getNickname(), this.authService.authenticate());
		return new BaseResBody<Void>(null, "user updated")
				.toResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<BaseResBody<Void>> deleteUser(@PathVariable(name = "userId") Integer userId, @RequestBody @Valid UserReqDto.Delete dto){
		this.userService.removeUser(userId, dto.getPassword(), this.authService.authenticate());
		return new BaseResBody<Void>(null, "user deleted")
				.toResponse(HttpStatus.OK);
	}
}
