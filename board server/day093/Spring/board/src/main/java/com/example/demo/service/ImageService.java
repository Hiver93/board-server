package com.example.demo.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;
	private final Set<String> ALLOWED_CONTENT = Set.of("image/jpeg", "image/png");
	
	public Image createImage(Post post, MultipartFile file) {
		if(!ALLOWED_CONTENT.contains(file.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID() + originalFileName;
		
		return this.imageRepository.save(Image.builder()
				.post(post)
				.fileName(fileName)
				.originalFileName(originalFileName)
				.build());
	}
}
