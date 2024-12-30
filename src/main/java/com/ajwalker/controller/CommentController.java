package com.ajwalker.controller;

import com.ajwalker.dto.request.AddCommentRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.entity.Comment;
import com.ajwalker.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
	private final CommentService commentService;
	
	@GetMapping(GETALLCOMMENT)
	public ResponseEntity<BaseResponse<List<CommentCardResponseDto>>> getAllComments(){ //Denied yada in review da olan userlar
		return ResponseEntity.ok(BaseResponse.<List<CommentCardResponseDto>>builder()
		                                     .message("Tum yorumların listesi")
		                                     .code(200)
		                                     .success(true)
		                                     .data(commentService.findAllComments())
		                                     .build());
	}
	
	@PostMapping(ADD_COMMENT)
	public ResponseEntity<BaseResponse<Boolean>> addComment(@RequestBody @Valid AddCommentRequestDto dto){
	return ResponseEntity.ok(BaseResponse.<Boolean>builder()
	                                     .message("Yorum başarıyla eklendi")
	                                     .code(200)
	                                     .success(true)
	                                     .data(commentService.addComment(dto))
	                                     .build());
	                                     
	}
}