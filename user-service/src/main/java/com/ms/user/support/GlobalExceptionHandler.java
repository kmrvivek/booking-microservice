package com.ms.user.support;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ms.core.common.ErrorResponse;
import com.ms.core.common.FeignClientException;
import com.ms.core.user.exception.InsufficientFundsException;
import com.ms.core.user.exception.NoSuchUserExistsException;
import com.ms.core.user.exception.UserAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { NoSuchUserExistsException.class, InsufficientFundsException.class,
			FeignClientException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(RuntimeException ex) {
		return ErrorResponse.builder().httpStatus(HttpStatus.NOT_FOUND).status(HttpStatus.NOT_FOUND.value())
				.errorMessages(Stream.of(ex.getMessage()).collect(Collectors.toList())).build();
	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponse handleException(UserAlreadyExistsException ex) {
		return ErrorResponse.builder().httpStatus(HttpStatus.CONFLICT).status(HttpStatus.CONFLICT.value())
				.errorMessages(Stream.of(ex.getMessage()).collect(Collectors.toList())).build();
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleOtherException(Exception ex) {
		return ErrorResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessages(Stream.of(ex.getMessage()).collect(Collectors.toList())).build();
	}

}
