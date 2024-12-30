package com.ajwalker.repository;

import com.ajwalker.entity.Comment;
import com.ajwalker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
	Optional<Comment> findByUserId(Long userId);
}