package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Post;

@Mapper
public interface PostMapper {

	@Insert("INSERT INTO post (title,content,password,view,created_at,updated_at) VALUES(#{title},#{content},#{password},#{view},now(),now())")
	public void save(Post post);
	
	@Select("SELECT * FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByTitle(@Param("pageable") Pageable pageable,@Param("keyword") String keyword);
	
	@Select("SELECT * FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') OR content LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByTitleOrContent(@Param("pageable") Pageable pageable,@Param("keyword") String keyword);
	
	@Select("SELECT * FROM post WHERE content LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByContent(@Param("pageable") Pageable pageable,@Param("keyword") String keyword);
	
	@Select("SELECT * FROM post "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAll(@Param("pageable") Pageable pageable);
	
	@Select("SELECT COUNT(*) FROM post WHERE title LIKE CONCAT('%',#{keyword},'%')")
	public long countByTitle(@Param("keyword") String keyword);
	
	@Select("SELECT count(*) FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') OR content LIKE CONCAT('%',#{keyword},'%')")
	public long countByTitleOrContent(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM post WHERE content LIKE CONCAT('%',#{keyword},'%')")
	public long countByContent(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM post")
	public long count();
	
}
