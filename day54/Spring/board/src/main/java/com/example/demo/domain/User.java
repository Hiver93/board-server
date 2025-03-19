package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
@EntityListeners(value = AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String nickname;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Post> postList;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	public User(Integer id, String username, String password, String nickname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getNickname() {
		return nickname;
	}
	public List<Post> getPostList() {
		return postList;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private Integer id;
		private String username;
		private String password;
		private String nickname;
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}
		public User build() {
			return new User(id, username, password, nickname);
		}
	}
}
