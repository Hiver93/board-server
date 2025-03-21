package com.example.board.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
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

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins="*")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public void postPost(@RequestBody PostRequestDto.Create dto) {
		this.postService.createPost(dto.toEntity());
	}
	
	@GetMapping
	public List<PostResponseDto.Get> getPostList(Pageable pageable,@RequestParam(name="keyword") String keyword,@RequestParam(name="target") Set<String> targets){
		return this.postService.getPostList(pageable, keyword, targets).stream()
				.map(PostResponseDto.Get::fromEntity)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{postId}")
	public PostResponseDto.Get getPost(@PathVariable(name="postId") long postId){
		return PostResponseDto.Get.fromEntity(this.postService.getPost(postId));
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable(name="postId") long postId, @RequestBody PostRequestDto.Delete dto) {
		this.postService.deletePost(postId, dto.getPassword());
	}
	
	@PutMapping("/{postId}")
	public void putPost(@PathVariable(name="postId") long postId, @RequestBody PostRequestDto.Update dto) {
		this.postService.modifyPost(dto.toEntity(postId));
	}
	
	@GetMapping("/total-size")
	public long getSize(@RequestParam(name="keyword") String keyword, @RequestParam(name="target")Set<String> targets) {
		return this.postService.countPost(keyword, targets);
	}
}
