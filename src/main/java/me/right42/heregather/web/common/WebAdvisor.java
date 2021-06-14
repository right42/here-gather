package me.right42.heregather.web.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.right42.heregather.exception.ApplicationException;

@ControllerAdvice
public class WebAdvisor {

	@ExceptionHandler(ApplicationException.class)
	public ErrorResponse applicationException(ApplicationException applicationException) {
		return new ErrorResponse();
	}

	private class ErrorResponse {
	}
}
