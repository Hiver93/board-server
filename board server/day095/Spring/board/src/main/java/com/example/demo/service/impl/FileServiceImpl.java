package com.example.demo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.example.demo.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	
	private final String DIR_PATH = "src/main/resources/static/images/";
	
	@Override
	public void saveFile(String fileName, byte[] bytes) {
		Path path = Paths.get(DIR_PATH + fileName);
		
		try {
			Files.createDirectories(path.getParent());
			Files.write(path, bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		Path path = Paths.get(DIR_PATH + fileName);
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
