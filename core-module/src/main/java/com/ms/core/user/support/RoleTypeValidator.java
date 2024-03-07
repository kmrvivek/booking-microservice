package com.ms.core.user.support;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ms.core.common.UserSupportedRole;

public class RoleTypeValidator implements ConstraintValidator<ValidateRoleType, String> {

	private List<String> valueList;

	@Override
	public void initialize(ValidateRoleType constraintAnnotation) {
		valueList = Stream.of(UserSupportedRole.values()).map(UserSupportedRole::name).collect(Collectors.toList());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return valueList.contains(value.toUpperCase());
	}

}
