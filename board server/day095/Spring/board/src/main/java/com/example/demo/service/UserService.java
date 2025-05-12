package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {

	public User createUser(String username, String password, String nickname);
	public User getUser(Integer userId);
	public User getUser(String username, String password);
	public void removeUser(Integer userId, String password, User user);
	public void modifyUser(Integer userId, String nickname, User user);
	public void removeInactivatedUser();
}
