package com.example.demo.service;

public interface FileService {

	public void saveFile(byte[] bytes, String fileName);
	public void deleteFile(String fileName);
}
