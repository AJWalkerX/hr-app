package com.ajwalker.controller;

import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.entity.Comment;
import com.ajwalker.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<BaseResponse<List<CommentCardResponseDto>>> getAllComments(){ //Denied yada in review da olan userlar
		return ResponseEntity.ok(BaseResponse.<List<CommentCardResponseDto>>builder()
		                                     .message("Tum yorumların listesi")
		                                     .code(200)
		                                     .success(true)
		                                     .data(commentService.findAllComments())
		                                     .build());
	}
}