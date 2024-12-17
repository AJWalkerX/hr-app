package com.ajwalker.controller;

import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.entity.Comment;
import com.ajwalker.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
	private final CommentService commentService;
	/**/
	
	@GetMapping(GETALLCOMMENT)
	public List<CommentCardResponseDto> getAllComments(){
	return 	commentService.findAllComments();
	}
}