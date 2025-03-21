package com.example.demo.controler;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BaseResBody;
import com.example.demo.dto.PostReqDto;
import com.example.demo.dto.PostResDto;
import com.example.demo.service.PostService;
import com.example.demo.util.AuthenticationUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<BaseResBody> postPost(@RequestBody @Valid PostReqDto.Create dto, HttpServletRequest request){
		AuthenticationUtil.authenticate(request);
		this.postService.createPost(dto.to());
		return BaseResBody.of(HttpStatus.CREATED, "posted");
	}
	
	@GetMapping
	public ResponseEntity<BaseResBody> getPostList(Pageable pageable, @RequestParam(name="keyword") String keyword, @RequestParam(name="target") Set<String> targets){
		List<PostResDto> result = this.postService.getPostList(pageable, keyword, targets).stream()
				.map(PostResDto::from)
				.toList();
		return BaseResBody.of(HttpStatus.OK, result, "get post list");
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BaseResBody> getPost(@PathVariable(name = "postId") Integer postId){
		PostResDto result = PostResDto.from(this.postService.getPost(postId));
		return BaseResBody.of(HttpStatus.OK, result, "get post");
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BaseResBody> deletePost(@PathVariable(name="postId") Integer postId, PostReqDto.Delete dto, HttpServletRequest request){
		AuthenticationUtil.authenticate(request);
		this.postService.deletePost(dto.to(postId));
		return BaseResBody.of(HttpStatus.OK, "deleted");
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BaseResBody> putPost(@PathVariable(name="postId") Integer postId, PostReqDto.Update dto, HttpServletRequest request){
		AuthenticationUtil.authenticate(request);
		this.postService.modifyPost(dto.to(postId));
		return BaseResBody.of(HttpStatus.OK, "updated");
	}
	
	@GetMapping("total-size")
	public ResponseEntity<BaseResBody> getTotalSize(@RequestParam(name="keyword") String keyword, @RequestParam(name="target") Set<String> targets){
		long result = this.postService.getTotalSize(keyword, targets);
		return BaseResBody.of(HttpStatus.OK, result, "get total size");
	}
	
}
