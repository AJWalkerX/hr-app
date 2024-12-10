package com.ajwalker.exception;

import lombok.Getter;

@Getter
public class HRAppException extends RuntimeException {
	private ErrorType errorType;
	
	public HRAppException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}