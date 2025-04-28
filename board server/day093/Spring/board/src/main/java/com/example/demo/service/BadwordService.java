package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BadwordService {

	private final List<String> badwordList = List.of("bad");
	
	public String mask(String str) {
		for(String badword : badwordList) {
			str = str.replaceAll(badword, "**");
		}
		return str;
	}
}
