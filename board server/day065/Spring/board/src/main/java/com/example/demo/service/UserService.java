package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void createUser(String username, String password, String nickname) {
		this.userRepository.findByUsername(username).ifPresent(u->{throw new BoardException(ErrorCode.USERNAME_CONFLICT);});
		this.userRepository.save(User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.nickname(nickname)
				.build());
	}
	
	public User getUser(String username, String password) {
		User saved = this.userRepository.findByUsername(username).orElseThrow(()->{throw new BoardException(ErrorCode.INVALIID_CREDENTIALS);});
		if(!passwordEncoder.matches(password, saved.getPassword())) {
			throw new BoardException(ErrorCode.INVALIID_CREDENTIALS);
		}
		return saved;
	}
	
	public User getUser(Integer userId) {
		User saved = this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
		return saved;
	}
	
	public void removeUser(Integer userId, User user) {
		User saved = this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
		if(saved.getId().equals(user.getId())) {
			throw new BoardException(ErrorCode.NOT_SAME_USER);
		}
		if(!passwordEncoder.matches(user.getPassword(), saved.getPassword())) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		this.userRepository.delete(saved);
	}
	
	@Transactional
	public void modifyUser(Integer userId, String nickname, User user) {
		User saved = this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
		if(saved.getId().equals(user.getId())) {
			throw new BoardException(ErrorCode.NOT_SAME_USER);
		}
		saved.updateNickname(nickname);
	}
}
