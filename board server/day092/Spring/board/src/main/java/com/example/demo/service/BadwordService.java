package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BadwordService {

	private final List<String> badwords = List.of("bad");
	
	public String maskBadword(String str) {
		for(String badword : badwords) {
			str = str.replaceAll(badword, "***");
		}
		return str;
	}
}
