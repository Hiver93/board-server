package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

import jakarta.persistence.LockModeType;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public Page<Post> findAllByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
	public Page<Post> findAllByTitleContaining(Pageable pageable, String title);
	public Page<Post> findAllByContentContaining(Pageable pageable, String content);
	
	public long countByTitleContainingOrContentContaining(String title, String content);
	public long countByTitleContaining(String title);
	public long countByContentContaining(String content);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Optional<Post> findWithPessimisticLockById(Integer id);
}
