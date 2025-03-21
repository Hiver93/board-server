package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostMapper;
import com.example.demo.repository.UserMapper;

@SpringBootTest
public class MapperTest {

	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test() {
		Pageable p = PageRequest.of(0, 3);
//		List<Post> pList = postMapper.findAllByTitleOrContent(p, "title");
//		pList.forEach(System.out::println);
		
//		postMapper.findAll(p).forEach(System.out::println);
		System.out.println(postMapper.findAll(p).get(0).getUser().getNickname());
	}
}
