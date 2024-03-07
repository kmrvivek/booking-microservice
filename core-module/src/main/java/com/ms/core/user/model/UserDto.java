package com.ms.core.user.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto {

	private String userId;
	@NotEmpty(message = "*Please provide user email")
	private String email;
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$", message = "*Your password is not strong enough!")
	@NotEmpty(message = "*Please provide user password")
	private String password;
	@NotEmpty(message = "*Please provide user first name")
	private String firstName;
	@NotEmpty(message = "*Please provide user contact number")
	private String contactNumber;
	@NotEmpty(message = "*Please provide user last name")
	private String lastName;
	@NotNull(message = "*Please provide user accountBalance")
	@DecimalMin(value = "0.0", inclusive = false, message = "*accountBalance cannot be negative")
	private BigDecimal accountBalance;
	private boolean active = true;
	
}
