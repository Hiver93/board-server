package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
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
	private final Set<String> ALLOWED_CONTENT = Set.of("image/jpeg", "image/png");
	private ImageRepository imageRepository;
	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}
	
	@Transactional
	public Image saveImage(Post post, MultipartFile image) throws IOException {
		if(!ALLOWED_CONTENT.contains(image.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		String originalFileName = image.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		
		Image saved = this.imageRepository.save(Image.builder()
				.post(post)
				.originalFileName(originalFileName)
				.fileName(fileName)
				.build());
		
		Path path = Paths.get(DIR_PATH + fileName);
		
		Files.createDirectories(path.getParent());
		Files.write(path, image.getBytes());
		return saved;
	}
}
