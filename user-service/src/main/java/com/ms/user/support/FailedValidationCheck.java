package com.ms.user.support;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ms.core.model.ErrorResponse;

@Aspect
@Component
public class FailedValidationCheck {

	@SuppressWarnings("unchecked")
	@Around("@annotation(FailedValidationHandler)")
	public ResponseEntity<Object> handle(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			if (object instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) object;
				if (bindingResult.hasErrors()) {
					List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
							.collect(Collectors.toList());
					return new ResponseEntity<>(
							new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errors),
							HttpStatus.BAD_REQUEST);
				}
			}
		}
		return (ResponseEntity<Object>) joinPoint.proceed();
	}
}
