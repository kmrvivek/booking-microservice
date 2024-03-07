package com.ms.core.user.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ms.core.user.model.AddressDto;

public class AddressTypeValidator implements ConstraintValidator<ValidateAddressDetails, AddressDto> {

	@Override
	public boolean isValid(final AddressDto value, final ConstraintValidatorContext context) {

		return UserSupportedAddress.isSupportedStateAndCityAndPincode(value.getState().toUpperCase(),
				value.getCity().toUpperCase(), value.getPincode().toUpperCase());
	}

}