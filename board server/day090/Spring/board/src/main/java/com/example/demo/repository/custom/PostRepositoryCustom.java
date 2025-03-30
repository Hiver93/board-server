package com.example.demo.repository.custom;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Post;

public interface PostRepositoryCustom {

	public Page<Post> findAllByKeyword(Pageable pageable, String keyword, Set<String> target);
}
