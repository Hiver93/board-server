package com.example.demo.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	private ImageService imageService;
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}


	@GetMapping("/{imageName}")
	public ResponseEntity<byte[]> getPost(@PathVariable(name = "imageName") String imageName) throws IOException{
		byte[] image = this.imageService.getImage(imageName);
		String imageType = imageName.substring(imageName.lastIndexOf(".")+1);
		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "image/"+imageType);
		
		return new ResponseEntity<byte[]>(image,header,HttpStatus.OK);
	}
}
