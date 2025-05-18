package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.domain.badword.BadwordTrie;
import com.example.demo.repository.BadwordRepository;
import com.example.demo.service.BadwordService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BadwordServiceImpl implements BadwordService{

	private final BadwordRepository badwordRepository;
	private BadwordTrie badwordTrie;
	
	@Override
	public String mask(String str) {
		return this.badwordTrie.maskBadword(str, '*');
	}

	@Override
	@PostConstruct
	public void updateBadwordSet() {
		this.badwordTrie = new BadwordTrie(this.badwordRepository.findAll());
	}
	
	

}
