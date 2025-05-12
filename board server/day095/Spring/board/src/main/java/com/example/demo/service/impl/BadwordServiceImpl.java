package com.example.demo.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.repository.BadwordRepository;
import com.example.demo.service.BadwordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BadwordServiceImpl implements BadwordService{
	
	private final BadwordRepository badwordRepository;
	
	@Override
	public String mask(String str) {
		Set<String> badwords = this.badwordRepository.findAll();
		
		for(String badword : badwords) {
			String replacement = "*".repeat(badword.length());
			str = str.replaceAll(badword, replacement);
		}
		return str;
	}

}
