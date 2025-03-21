package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto.Profile;
import com.example.demo.facade.UserFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserFacade userFacade;

	public UserController(UserFacade userFacade) {
		super();
		this.userFacade = userFacade;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResBody<Void>> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userFacade.signup(dto);
		return new BaseResBody<Void>(null, "signed up")
				.toResponse(HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResBody<Void>> login(@RequestBody @Valid UserReqDto.Login dto){
		this.userFacade.login(dto);
		return new BaseResBody<Void>(null, "loged in")
				.toResponse(HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<BaseResBody<Profile>> getProfile(@PathVariable(name = "userId") Integer userId){
		
		return new BaseResBody<>(
				this.userFacade.getProfile(userId), 
				"get profile")
				.toResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<BaseResBody<Void>> deleteUser(@PathVariable(name = "userId") Integer userId, @RequestBody @Valid UserReqDto.Delete dto){
		this.userFacade.removeUser(userId, dto);
		return new BaseResBody<Void>(
				null, 
				"user deleted")
				.toResponse(HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<BaseResBody<Void>> putUser(@PathVariable(name = "userId") Integer userId, @RequestBody @Valid UserReqDto.Put dto){
		this.userFacade.modifyUser(userId, dto);
		return new BaseResBody<Void>(
				null, 
				"user modified")
				.toResponse(HttpStatus.OK);
	}
}
