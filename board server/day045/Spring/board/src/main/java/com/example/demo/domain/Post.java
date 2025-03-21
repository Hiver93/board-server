package com.example.demo.domain;

import java.time.LocalDateTime;

public class Post {

	private Integer id;
	private String title;
	private String content;
	private Integer view;
	private User user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Post(Integer id, String title, String content, Integer view, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.view = view;
		this.user = user;
	}
	public Post() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Integer getView() {
		return view;
	}
	public User getUser() {
		return user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void increaseView() {
		this.view = this.view+1;
	}
	public void updateTitle(String title) {
		this.title = title;
	}
	public void updateContent(String content) {
		this.content = content;
	}
	public static Builder builder() {
		return new Builder();
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", view=" + view + ", user=" + user
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public static class Builder{
		private Integer id;
		private String title;
		private String content;
		private Integer view = 0;
		private User user;
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		public Builder user(User user) {
			this.user = user;
			return this;
		}
		public Post build() {
			return new Post(id,title,content,view,user);
		}
	}
}
