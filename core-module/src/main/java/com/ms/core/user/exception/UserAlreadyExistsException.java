package com.ms.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User Already Exists")
public class UserAlreadyExistsException extends RuntimeException {
 
	private static final long serialVersionUID = -1468261024111894986L;
 
    public UserAlreadyExistsException() {}
 
    public UserAlreadyExistsException(String msg)
    {
        super(msg);
    }
}