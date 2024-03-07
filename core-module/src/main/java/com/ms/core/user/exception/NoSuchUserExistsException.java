package com.ms.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No Such User Exists")
public class NoSuchUserExistsException extends RuntimeException {

	private static final long serialVersionUID = 1421261546339307240L;

	public NoSuchUserExistsException() {
	}

	public NoSuchUserExistsException(String msg) {
		super(msg);
	}
}
