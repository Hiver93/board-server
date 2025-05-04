package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	public final UserRepository userRepository;
	public final PasswordEncoder passwordEncoder;
	@Override
	public User createUser(String username, String password, String nickname) {
		if(this.userRepository.existsByUsername(username)) {
			throw new BoardException(ErrorCode.USERNAME_CONFLICT);
		}
		
		return this.userRepository.save(User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.nickname(nickname)
				.build());
	}
	@Override
	public User getUser(Integer userId) {

		return this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
	}
	@Override
	public User getUser(String username, String password) {
		User saved = this.userRepository.findByUsername(username).orElseThrow(()->{throw new BoardException(ErrorCode.INVALID_CREDENTIALS);});
		if(!passwordEncoder.matches(password, saved.getPassword())) {
			throw new BoardException(ErrorCode.INVALID_CREDENTIALS);
		}
		return saved;
	}
	@Override
	public void removeUser(Integer userId, String password, User user) {
		User saved = this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
		if(!saved.isSameUser(user)) {
			throw new BoardException(ErrorCode.USER_NOT_SAME);
		}
		if(!passwordEncoder.matches(password, saved.getPassword())) {
			throw new BoardException(ErrorCode.INCORRECT_PASSWORD);
		}
		this.userRepository.delete(saved);
	}
	@Override
	@Transactional
	public void modifyUser(Integer userId, String nickname, User user) {
		User saved = this.userRepository.findById(userId).orElseThrow(()->{throw new BoardException(ErrorCode.USER_NOT_FOUND);});
		if(!saved.isSameUser(user)) {
			throw new BoardException(ErrorCode.USER_NOT_SAME);
		}
		saved.updateNickname(nickname);
	}
	@Override
	@Transactional
	@Scheduled(cron = "0 0 * * * *")
	public void removeInactivatedUser() {
		this.userRepository.deleteInactivatedUserOrderThan(LocalDateTime.now().minusHours(1));
	}
	
}
