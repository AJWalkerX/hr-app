package com.ajwalker.service;

import com.ajwalker.entity.Comment;
import com.ajwalker.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	public List<Comment> findAllComments() {
		return commentRepository.findAll();
	}
}