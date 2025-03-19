package com.example.board.domain;

public class Post {
	private long id;
	private String title;
	private String content;
	public Post(long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	public Post() {}
}
