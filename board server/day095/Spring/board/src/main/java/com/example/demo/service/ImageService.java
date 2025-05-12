package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.domain.Post;

public interface ImageService {

	public Image createImage(Post post, MultipartFile file);
}
