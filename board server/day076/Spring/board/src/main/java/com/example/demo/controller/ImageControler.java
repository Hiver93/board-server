package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageControler {

	private ImageService imageService;

	public ImageControler(ImageService imageService) {
		super();
		this.imageService = imageService;
	}
	
	@GetMapping("/{imageId}")
	public byte[] getImage(@PathVariable(name = "imageId") Integer imageId) throws FileNotFoundException, IOException {
		return this.imageService.getImage(imageId);
	}
}
