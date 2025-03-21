package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.service.UserService;
import com.example.demo.util.AuthenticationUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResBody> Signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.createUser(dto.to());
		return BaseResBody.of(HttpStatus.CREATED, "signed up");
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResBody> login(@RequestBody @Valid UserReqDto.Login dto, HttpServletRequest request){
		var result = UserResDto.Login.from(this.userService.getUser(dto.to()));
		AuthenticationUtil.setUserId(request, result.id());
		return BaseResBody.of(HttpStatus.OK, result, "loged in");
	}
	
	@GetMapping("/status")
	public ResponseEntity<BaseResBody> getStatus(HttpServletRequest request){
		var result = UserResDto.Status.from(this.userService.getUser(AuthenticationUtil.getUserId(request)));
		return BaseResBody.of(HttpStatus.OK,result, "getStatus");
	}
	
	@PostMapping("/logout")
	public ResponseEntity<BaseResBody> logout(HttpServletRequest request){
		request.getSession().invalidate();
		return BaseResBody.of(HttpStatus.OK, "loged out");				
	}
}
