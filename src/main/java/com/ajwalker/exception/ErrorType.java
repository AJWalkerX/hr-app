package com.ajwalker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyin.", HttpStatus.BAD_REQUEST),
	INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
	PASSWORD_ERROR(6001, "girilen şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
	INVALID_EMAIL_OR_PASSWORD(6002,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
	NOTFOUND_USER(6003,"kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
	DENIED_USER(6100,"Hesap onayi reddedildi! Daha fazla bilgi icin iletisime geciniz", HttpStatus.FORBIDDEN),
	IN_REVIEW_USER(6120,"Hesap onayi incelemede! 15 gun icerisinde iletisime gececegiz", HttpStatus.BAD_GATEWAY),
	PENDING_USER(6130,"Giris yapmadan once email onayi yapmaniz gerekmektedir!", HttpStatus.BAD_GATEWAY),
	INVALID_ADMIN(7001,"Admin adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
	INVALID_TOKEN(9001,"geçersiz token bilgisi",HttpStatus.BAD_REQUEST);
	
	
	int code;
	String message;
	HttpStatus httpStatus;
}