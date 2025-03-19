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

	private final String DIR_PATH = "src/main/resources/static/images/";
	private ImageRepository imageRepository;
	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}


	public Image saveImage(MultipartFile image) throws IOException {
		String originalFileName = image.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		
		Path path = Paths.get(DIR_PATH+fileName);
		Files.createDirectories(path.getParent());
		Files.write(path, image.getBytes());
		return Image.builder()
				.fileName(fileName)
				.originalFileName(originalFileName)
				.build();
	}
	
	public byte[] getImage(Integer imageId) throws FileNotFoundException, IOException {
		Image saved = this.imageRepository.findById(imageId).orElseThrow();
		try(InputStream stream = new FileInputStream(DIR_PATH+saved.getFileName())){
			return stream.readAllBytes();
		}
	}
	
}
