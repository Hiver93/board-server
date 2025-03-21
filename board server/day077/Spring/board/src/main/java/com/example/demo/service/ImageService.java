package com.example.demo.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageService {

	private final String DIR_PATH = "src/main/resources/static/images/";
	private List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png");
	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}
	
	@Transactional
	public Image saveImage(MultipartFile image, Post post) throws IOException {
		if(!ALLOWED_TYPES.contains(image.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		String originalFileName = image.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		
		Image saved = this.imageRepository.save(Image.builder()
				.fileName(fileName)
				.originalFileName(originalFileName)
				.post(post)
				.build());
		
		Path path = Paths.get(DIR_PATH + fileName);
		
		Files.createDirectories(path.getParent());
		Files.write(path, image.getBytes());
		
		return saved;
	}
	
	public byte[] getImage(String fileName) throws IOException {
		try(InputStream stream  = new FileInputStream(DIR_PATH + fileName)){
			return stream.readAllBytes();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("file not found : " + fileName);
		} 
	}
}
