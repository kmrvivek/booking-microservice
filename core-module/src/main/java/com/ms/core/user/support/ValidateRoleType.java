package com.ms.core.user.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = RoleTypeValidator.class)
@Target( { ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Role cannot be null")
@ReportAsSingleViolation
public @interface ValidateRoleType {

	String message() default "Value is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
