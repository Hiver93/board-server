package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.body.BaseResBody;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins="*")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody> postPost(@RequestBody @Valid PostReqDto.Create dto){
		this.postService.createPost(dto.toEntity());
		return BaseResBody.buildResponseEntity(HttpStatus.CREATED, "created");
	}
	
	@GetMapping
	public ResponseEntity<BaseResBody> getPostList(Pageable pageable,@RequestParam("keyword") String keyword,@RequestParam("target") Set<String> target){
		List<PostResDto> result = this.postService.getPostList(pageable, keyword, target).stream()
				.map(PostResDto::fromEntity)
				.toList();
		return BaseResBody.buildResponseEntity(HttpStatus.OK, result, "success");
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BaseResBody> getPost(@PathVariable(name="postId") long postId){
		PostResDto result = PostResDto.fromEntity(this.postService.getPost(postId));
		return BaseResBody.buildResponseEntity(HttpStatus.OK, result, "success");
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BaseResBody> deletePost(@PathVariable(name="postId") long postId,@RequestBody @Valid PostReqDto.Delete dto){
		this.postService.deletePost(dto.toEntity(postId));
		return BaseResBody.buildResponseEntity(HttpStatus.OK, "deleted");
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BaseResBody> putPost(@PathVariable(name="postId") long postId,@RequestBody @Valid PostReqDto.Update dto){
		this.postService.modifyPost(dto.toEntity(postId));
		return BaseResBody.buildResponseEntity(HttpStatus.OK, "updated");
	}
	
	@GetMapping("/total-size")
	public ResponseEntity<BaseResBody> getTotalSize(@RequestParam("keyword") String keyword,@RequestParam("target") Set<String> target){
		long result = this.postService.getTotalSize(keyword, target);
		return BaseResBody.buildResponseEntity(HttpStatus.OK, result, "success");
	}
}
