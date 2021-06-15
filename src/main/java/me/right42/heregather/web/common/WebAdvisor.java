package me.right42.heregather.web.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Data;
import lombok.Getter;
import me.right42.heregather.exception.ApplicationException;

@RestControllerAdvice
public class WebAdvisor {

	@ExceptionHandler(ApplicationException.class)
	public ErrorResponse applicationException(ApplicationException applicationException) {
		return ErrorResponse.of(applicationException.getMessage());
	}

	@Getter
	static class ErrorResponse {
		private final String message;

		private ErrorResponse(String message) {
			this.message = message;
		}

		public static ErrorResponse of(String message) {
			return new ErrorResponse(message);
		}
	}
}
