package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class AuthRepository {

	Map<String, User> map = new HashMap<>();
	
	public void save(String uuid, User user) {
		this.map.put(uuid, user);
	}
	
	public Optional<User> findByUuid(String uuid) {
		return Optional.ofNullable(this.map.get(uuid));
	}
}
