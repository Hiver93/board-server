package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class PostLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Post post;
	@CreatedDate
	private LocalDateTime createdAt;
	public PostLike(Integer id, User user, Post post) {
		super();
		this.id = id;
		this.user = user;
		this.post = post;
	}
	public PostLike() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Post getPost() {
		return post;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private Integer id;
		private User user;
		private Post post;
		public Builder id(Integer id) {
			this.id= id;
			return this;
		}
		public Builder user(User user) {
			this.user = user;
			return this;
		}
		public Builder post(Post post) {
			this.post =post;
			return this;
		}
		public PostLike build() {
			return new PostLike(id, user, post);
		}
	}
}
