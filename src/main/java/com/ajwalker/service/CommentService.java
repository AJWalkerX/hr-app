package com.ajwalker.service;

import com.ajwalker.dto.request.AddCommentRequestDto;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.dto.response.CommentUserCardResponse;
import com.ajwalker.entity.Comment;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.CommentRepository;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.Enum.user.EPosition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final UserService userService;
	private final CompanyService companyService;
	private final PersonalDocumentService personalDocumentService;

	public List<CommentCardResponseDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(comment -> {
			// Yorumun userId'si ile kişisel doküman bilgisini alıyoruz
			PersonalDocument personalDocument = personalDocumentService
					.findById(comment.getUserId())
					.orElseThrow(() -> new RuntimeException("Personal document not found for userId: " + comment.getUserId()));
			
			// User bilgilerini almak için userRepository kullanıyoruz
			User user = userService.findById(comment.getUserId())
			                          .orElseThrow(() -> new RuntimeException("User not found for comment ID: " + comment.getId()));
			
			// Şirket bilgisini almak için companyRepository kullanıyoruz
			Company company = companyService.findById(comment.getCompanyId())
			                                   .orElseThrow(() -> new RuntimeException("Company not found for comment ID: " + comment.getId()));
			
			// DTO'ya veri ekliyoruz
			return new CommentCardResponseDto(
					company.getCompanyLogo(),  // Şirket logosu dinamik
					comment.getContent(),      // Yorum içeriği
					personalDocument.getFirstName(),      // Kullanıcı adı
					personalDocument.getLastName(),       // Kullanıcı soyadı
					personalDocument.getPosition().toString(),  // Pozisyon (Enum'dan string'e çeviriyoruz)
					user.getAvatar()          // Kullanıcı avatarı
			);
		}).collect(Collectors.toList());
	}
	
	public Boolean addComment(AddCommentRequestDto dto, Long managerId) {
		User user = userService.findById(managerId).get();
		if (user.getCompanyId() == null) {
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		
		Optional<Comment> existingComment = commentRepository.findByUserId(user.getId());
		
		if (existingComment.isPresent()) {
			commentRepository.delete(existingComment.get());
		}
		
		
		Comment comment = Comment.builder()
		                         .userId(managerId)
		                         .companyId(user.getCompanyId())
		                         .content(dto.content())
		                         .description(dto.description())
		                         .commentDate(LocalDate.now())
		                         .build();
		
		commentRepository.save(comment);
		
		return true;
	}

	public List<CommentUserCardResponse> findAllUserComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
				.filter(comment -> {
					PersonalDocument personalDocument = personalDocumentService
							.findById(comment.getUserId())
							.orElse(null);

					return personalDocument != null && personalDocument.getPosition() == EPosition.MANAGER;
				})
				.map(comment -> {
					PersonalDocument personalDocument = personalDocumentService
							.findById(comment.getUserId())
							.orElseThrow(() -> new RuntimeException("Personal document not found for userId: " + comment.getUserId()));

					User user = userService.findById(comment.getUserId())
							.orElseThrow(() -> new RuntimeException("User not found for comment ID: " + comment.getId()));

					Company company = companyService.findById(comment.getCompanyId())
							.orElseThrow(() -> new RuntimeException("Company not found for comment ID: " + comment.getId()));

					return new CommentUserCardResponse(
							personalDocument.getFirstName(),
							personalDocument.getLastName(),
							company.getCompanyName(),
							personalDocument.getPosition().toString(),
							user.getAvatar()
					);
				})
				.collect(Collectors.toList());
	}

}