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
	INVALID_TOKEN(9001,"geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
	NOTFOUND_PERSONALDOCUMENT(6013,"PersonelDocument bulunamadı",HttpStatus.BAD_REQUEST),
	NOTFOUND_COMPANY(3003,"Şirket bulunamadı",HttpStatus.BAD_REQUEST),
	ALREADY_EXIST_COMPANY(3003,"Şirket Siteme kayitli! Kayit olmadan once sirketiniz ile iletisime geciniz!",HttpStatus.BAD_REQUEST),
	NOTFOUND_MEMBERSHIP_PLAN(2003,"Üyelik planı bulunamadı",HttpStatus.BAD_REQUEST),
	NOTFOUND_MANAGER(5003,"Manager bulunamadı!!!",HttpStatus.BAD_REQUEST),
	NOTFOUND_WORKHOLIDAY_INPENDING(7003,"Herhangi bir izin isteği bulunamadı!!!",HttpStatus.BAD_REQUEST),
	DENIED_DELETE_USER(6110,"Kendinizi silemezsiniz!",HttpStatus.BAD_REQUEST),
	PHOTO_SIZE_ERROR(101,"Fotoğraf boyutu 5MB'dan  fazla olmamalıdır!",HttpStatus.BAD_REQUEST),
	INVALID_PHOTO_TYPE(102, "Dosya formati .png yada .jpg olmalidir!", HttpStatus.BAD_REQUEST),
	NOTFOUND_COMMENT(8001, "Yorum bulunamdı!", HttpStatus.BAD_REQUEST),
	NOTFOUND_SPENDING(6103,"Kullanıcıya ait harcama bulunamadı",HttpStatus.BAD_REQUEST),
	NOTFOUND_SALARY(6203,"Kullanıcıya ait aylık maaş bulunamadı",HttpStatus.BAD_REQUEST),
	NOTFOUND_SHIFT(4003,"Boyle bir vardiya bulunamadi!",HttpStatus.BAD_REQUEST),
	OUTOFBOUNDRY_SHIFT_HOURS(4004,"24 saati gecicek sekilde vardiya atamasi yapamazsiniz!",HttpStatus.BAD_REQUEST),
	MANAGER_AND_PERSONAL_NOT_SAME_COMPANY(6201,"manager ve personal aynı şirkette değiller !!!",HttpStatus.BAD_REQUEST),
	NOT_FOUND_EMBEZZLEMENT(5001,"Zimmet eşyası bulunamadı",HttpStatus.BAD_REQUEST);
	
	int code;
	String message;
	HttpStatus httpStatus;
}