package com.example.demo.service;

import com.example.demo.domain.User;

public interface AuthService {

	public void setAuthentication(User user);
	public boolean isAuthenticated();
	public User getAuthentication();
	public void remoevAuthentication();
}
