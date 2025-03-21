package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.BaseResBody;
import com.example.demo.service.FileService;

@RestController
@RequestMapping("/images")
public class ImageController {

	private FileService fileService;

	public ImageController(FileService fileService) {
		super();
		this.fileService = fileService;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody<Void>> saveImage(@RequestPart("file") MultipartFile file){
		this.fileService.saveFile(file);
		return new BaseResBody<Void>(null, "saved")
				.toResponse(HttpStatus.CREATED);
	}
	
	@GetMapping("/{fileId}")
	public byte[] saveImage(@PathVariable(name = "fileId") Integer fileId){
		
		return this.fileService.getFile(fileId);
	}
}
