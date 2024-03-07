package com.ms.user.support;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Trigger the validation
 * 
 * @see {#link {@link FailedValidationCheck}}}
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface FailedValidationHandler {

}
