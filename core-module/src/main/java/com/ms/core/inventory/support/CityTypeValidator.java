package com.ms.core.inventory.support;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CityTypeValidator implements ConstraintValidator<ValidateCity, String> {

	private List<String> valueList;

	@Override
	public void initialize(ValidateCity constraintAnnotation) {
		valueList = Stream.of(OperatingCity.values()).map(OperatingCity::name).collect(Collectors.toList());
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return valueList.contains(value.toUpperCase());
	}

}
