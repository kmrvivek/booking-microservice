package com.ms.core.inventory.support;

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
@Constraint(validatedBy = CityTypeValidator.class)
@Target( { ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "city cannot be null")
@ReportAsSingleViolation
public @interface ValidateCity {

	String message() default "city provided is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
