package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Post;

@Mapper
public interface PostMapper {

	@Insert("INSERT INTO post (title, content, password, view, created_at, updated_at) "
			+ "VALUES(#{post.title}, #{post.content}, #{post.password}, 0, now(), now())")
	public void save(@Param("post") Post post);
	
	@Select("SELECT * FROM post "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAll(@Param("pageable") Pageable pageable);
	
	@Select("SELECT * FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') OR content LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByTitleOrContent(@Param("pageable") Pageable pageable, @Param("keyword") String keyword);
	
	@Select("SELECT * FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByTitle(@Param("pageable") Pageable pageable, @Param("keyword") String keyword);
	
	@Select("SELECT * FROM post WHERE content LIKE CONCAT('%',#{keyword},'%') "
			+ "ORDER BY created_at DESC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}")
	public List<Post> findAllByContent(@Param("pageable") Pageable pageable, @Param("keyword") String keyword);
	
	@Select("SELECT * FROM post WHERE id = #{id}")
	public Optional<Post> findById(@Param("id") long id);
	
	@Select("SELECT COUNT(*) FROM post")
	public long count();
	
	@Select("SELECT COUNT(*) FROM post WHERE title LIKE CONCAT('%',#{keyword},'%') OR content LIKE CONCAT('%',#{keyword},'%')")
	public long countByTitleOrContent(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM post WHERE title LIKE CONCAT('%',#{keyword},'%')")
	public long countByTitle(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM post WHERE content LIKE CONCAT('%',#{keyword},'%')")
	public long countByContent(@Param("keyword") String keyword);
	
	@Delete("DELETE FROM post WHERE id = #{id}")
	public void deleteById(@Param("id") long id);
	
	@Update("UPDATE post SET title=#{post.title}, content=#{post.content} WHERE id = #{post.id}")
	public void update(@Param("post") Post post);
	
}
