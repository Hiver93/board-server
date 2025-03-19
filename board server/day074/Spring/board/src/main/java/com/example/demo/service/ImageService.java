package com.example.demo.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageService {

	private final String PATH = "src/main/resources/static/images/"; 
	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}
	
	public void saveImage(MultipartFile file) throws IOException {
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		Path path = Paths.get(PATH + fileName);
		Files.createDirectories(path.getParent());
		Files.write(path, file.getBytes());
		this.imageRepository.save(new Image(null,fileName, originalFileName));
	}
	
	public byte[] getImage(Integer imageId) throws FileNotFoundException, IOException {
		String fileName = this.imageRepository.findById(imageId).orElseThrow().getFileName();
		try(InputStream stream = new FileInputStream(PATH + fileName)){
			return stream.readAllBytes();
		}
	}
}
