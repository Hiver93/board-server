package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUsername(String username);
	
	@Modifying
	@Query(value = "DELETE FROM User u WHERE u.deletedAt <= :time")
	public void deleteInactivatedUserOlderThan(@Param("time") LocalDateTime time);
}
