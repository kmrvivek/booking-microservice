package com.ms.core.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error Occured during inter-service communication using FeignClients")
public class FeignClientException extends RuntimeException {

	private static final long serialVersionUID = -2745953955327738338L;

	public FeignClientException() {

	}

	public FeignClientException(String msg) {
		super(msg);
	}
}