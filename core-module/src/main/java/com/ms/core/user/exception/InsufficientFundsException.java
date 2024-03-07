package com.ms.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Requested User doesn't have sufficient account Balance")
public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 4114902188728348677L;

	public InsufficientFundsException() {
	}

	public InsufficientFundsException(String msg) {
		super(msg);
	}
}
