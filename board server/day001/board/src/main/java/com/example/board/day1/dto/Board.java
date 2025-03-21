package com.example.board.day1.dto;

public class Board {
	private long id;
	private String title;
	private String content;
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
	public Board(long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public Board() {
		
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
