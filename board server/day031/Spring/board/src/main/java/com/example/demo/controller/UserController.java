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
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.service.UserService;

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
	public ResponseEntity<BaseResBody> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.createUser(dto.toEntity());
		return BaseResBody.buildResponseEntity(HttpStatus.CREATED, "signed up");
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResBody> login(@RequestBody @Valid UserReqDto.Login dto, HttpServletRequest request){
		UserResDto.Login result = UserResDto.Login.fromEntity(this.userService.getUser(dto.toEntity()));
		request.getSession().setAttribute("userId", result.id());
		request.getSession().setMaxInactiveInterval(30 * 60);
		return BaseResBody.buildResponseEntity(HttpStatus.OK, result, "loged in");
	}
	
	@GetMapping("/status")
	public ResponseEntity<BaseResBody> getStatus(HttpServletRequest request){
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		if(userId == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		UserResDto.Status result = UserResDto.Status.fromEntity(this.userService.getUser(userId));
		return BaseResBody.buildResponseEntity(HttpStatus.OK, result, "get status");
	}
	
	@PostMapping("/logout")
	public ResponseEntity<BaseResBody> logout(HttpServletRequest request){
		request.getSession().invalidate();
		return BaseResBody.buildResponseEntity(HttpStatus.OK, "loged out");
	}
	
}
