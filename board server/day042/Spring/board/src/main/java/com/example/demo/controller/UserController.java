package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.domain.User;
import com.example.demo.dto.PostResDto;
import com.example.demo.dto.UserReqDto;
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
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResBody> signup(@RequestBody @Valid UserReqDto.Signup dto){
		this.userService.createUser(dto.to());
		return BaseResBody.of(HttpStatus.CREATED, "signed up");
	}
	@PostMapping("/login")
	public ResponseEntity<BaseResBody> login(@RequestBody @Valid UserReqDto.Login dto){
		User user = this.userService.getUser(dto.to());
		this.authService.setAuthentication(user);
		return BaseResBody.of(HttpStatus.OK, "loged in");
	}
	@PostMapping("/reissue")
	public ResponseEntity<BaseResBody> reissue(){
		this.authService.refreshAuthentication();
		return BaseResBody.of(HttpStatus.OK, "reissued");
	}
	@GetMapping
	public ResponseEntity<BaseResBody> getProfile(){
		User user = this.userService.getUser(authService.authenticate().getId());
		return BaseResBody.of(HttpStatus.OK, new Object() {
			Integer id = user.getId();
			String nickname = user.getNickname();
			List<PostResDto> postList = user.getPostList().stream()
					.map(PostResDto::from)
					.toList();
			public Integer getId() {
				return id;
			}
			public String getNickname() {
				return nickname;
			}
			public List<PostResDto> getPostList() {
				return postList;
			}
			
			
		}, "profile");
	}
}
