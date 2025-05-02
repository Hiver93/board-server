package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;
import com.example.demo.repository.criteria.PostRepositoryCriteria;

import jakarta.persistence.LockModeType;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCriteria{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Post findWithLockById(Integer id);
}
