package com.example.demo.service;

public interface FileService {

	public void saveFile(String fileName, byte[] bytes);
	public void deleteFile(String fileName);
}
