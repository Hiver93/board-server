package com.example.demo.service;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ImageRepository;
import com.example.demo.util.ImageUtil;

@Service
public class ImageService {

	private final Set<String> ALLOWED_CONTENT = Set.of("image/png", "image/jpeg");
	private ImageRepository imageRepository;	
	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}


	public Image saveImage(Post post, MultipartFile file) throws IOException {
		if(!ALLOWED_CONTENT.contains(file.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		
		Image saved = this.imageRepository.save(Image.builder()
				.originalFileName(originalFileName)
				.fileName(fileName)
				.post(post)
				.build());
		
		ImageUtil.saveImage(fileName, file.getBytes());
		return saved;
	}
}
