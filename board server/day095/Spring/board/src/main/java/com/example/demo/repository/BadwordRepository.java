package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BadwordRepository {

	private final RedisTemplate<String, String> redisTemplate;
	private final String BADWORD_KEY = "badwords";
	
	public Set<String> findAll(){
		return redisTemplate.opsForSet().members(BADWORD_KEY);
	}
}
