package com.example.demo.service.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

	private final ImageRepository imageRepository;
	private final Set<String> ALLOWED_CONTENT = Set.of("image/png","image/jpeg");

	@Override
	public Image createImage(Post post, MultipartFile file) {
		if(!ALLOWED_CONTENT.contains(file.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + originalFileName;
		
		return this.imageRepository.save(Image.builder()
				.post(post)
				.fileName(fileName)
				.originalFileName(originalFileName)
				.build());
	}

	@Override
	public void removeImage(Integer imageId) {
		Image image = this.imageRepository.findById(imageId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		this.imageRepository.delete(image);
	}
	
}
