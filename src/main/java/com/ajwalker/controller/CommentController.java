package com.ajwalker.controller;

import com.ajwalker.dto.request.AddCommentRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.dto.response.CommentDetailsResponseDto;
import com.ajwalker.dto.response.CommentUserCardResponse;
import com.ajwalker.entity.Comment;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.CommentService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
	private final CommentService commentService;
	private final JwtManager jwtManager;
	
	@GetMapping(GETALLCOMMENT)
	public ResponseEntity<BaseResponse<List<CommentCardResponseDto>>> getAllComments(){
		return ResponseEntity.ok(BaseResponse.<List<CommentCardResponseDto>>builder()
		                                     .message("Tum yorumların listesi")
		                                     .code(200)
		                                     .success(true)
		                                     .data(commentService.findAllComments())
		                                     .build());
	}
	
	@PostMapping(ADD_COMMENT)
	public ResponseEntity<BaseResponse<Boolean>> addComment(@RequestBody @Valid AddCommentRequestDto dto){
		Optional<Long> managerId = jwtManager.verifyToken(dto.token());
		if (managerId.isEmpty()){
			throw  new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
	                                     .message("Yorum başarıyla eklendi")
	                                     .code(200)
	                                     .success(true)
	                                     .data(commentService.addComment(dto,managerId.get()))
	                                     .build());
	                                     
	}

	@GetMapping(GETUSERALLCOMMENT)
	public ResponseEntity<BaseResponse<List<CommentUserCardResponse>>> getAllUserComments(){
		return ResponseEntity.ok(BaseResponse.<List<CommentUserCardResponse>>builder()
				.message("Tüm Managerlerin listesi.")
				.code(200)
				.success(true)
				.data(commentService.findAllUserComments())
				.build());
	}
	
	@GetMapping(GETCOMMENTDETAILS)
	public ResponseEntity<BaseResponse<CommentDetailsResponseDto>> getAllCommentDetails(@RequestParam Long commentId){
		return ResponseEntity.ok(BaseResponse.<CommentDetailsResponseDto>builder()
				                         .message("Detaylı yorumlar listelendi.")
				                         .code(200)
				                         .success(true)
				                         .data(commentService.getCommentDetails(commentId)).build());
	}
}