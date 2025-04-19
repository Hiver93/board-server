package com.example.demo.service;

import java.io.IOException;
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
import com.example.demo.util.ImageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final Set<String> ALLOWED_CONTENT = Set.of("image/png", "image/jpeg");
	private final ImageRepository imageRepository;
	
	@Transactional
	public Image createImage(Post post, MultipartFile file) throws IOException {
		if(!ALLOWED_CONTENT.contains(file.getContentType())) {
			throw new BoardException(ErrorCode.INVALID_IMAGE_TYPE);
		}
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID() + originalFileName;
		
		Image saved = this.imageRepository.save(Image.builder()
				.originalFileName(originalFileName)
				.fileName(fileName)
				.post(post)
				.build());
		ImageUtil.saveImage(file.getBytes(), fileName);
		return saved;
	}
}
