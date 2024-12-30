package com.ajwalker.service;

import com.ajwalker.dto.request.AddCommentRequestDto;
import com.ajwalker.dto.response.CommentCardResponseDto;
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
	private final PersonalDocumentRepository personalDocumentRepository;
	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	private final ManagerService managerService;
	
	
	public List<CommentCardResponseDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(comment -> {
			// Yorumun userId'si ile kişisel doküman bilgisini alıyoruz
			PersonalDocument personalDocument = personalDocumentRepository
					.findById(comment.getUserId())
					.orElseThrow(() -> new RuntimeException("Personal document not found for userId: " + comment.getUserId()));
			
			// User bilgilerini almak için userRepository kullanıyoruz
			User user = userRepository.findById(comment.getUserId())
			                          .orElseThrow(() -> new RuntimeException("User not found for comment ID: " + comment.getId()));
			
			// Şirket bilgisini almak için companyRepository kullanıyoruz
			Company company = companyRepository.findById(comment.getCompanyId())
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
	
	public Boolean addComment(AddCommentRequestDto dto) {
		// Kullanıcıyı ID üzerinden bul
		Optional<User> optUser = userService.findUserById(dto.userId());
		if (optUser.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		
		// Kullanıcıyı elde et
		User user = optUser.get();
		
		// Kullanıcının company_id bilgisini al
		if (user.getCompanyId() == null) {
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		
		// Yorum nesnesini oluştur
		Comment comment = Comment.builder()
		                         .userId(dto.userId())
		                         .companyId(user.getCompanyId()) // Kullanıcının şirket ID'sini ekle
		                         .content(dto.content())
		                         .description(dto.description())
		                         .commentDate(LocalDate.now())
		                         .build();
		
		// Yorum kaydedilir
		commentRepository.save(comment);
		
		return true;
	}
	
	
}