package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;
import com.example.demo.repository.custom.PostRepositoryCustom;

import jakarta.persistence.LockModeType;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Optional<Post> findWithLockById(Integer id);
}
