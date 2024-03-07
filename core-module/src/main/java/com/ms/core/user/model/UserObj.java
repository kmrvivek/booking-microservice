package com.ms.core.user.model;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;

@Data
public class UserObj {

	private String userId;
	private String email;
	private String password;
	private String firstName;
	private String contactNumber;
	private String lastName;
	private BigDecimal accountBalance;
	private boolean active;
	private Set<RoleDto> roles;
	private Set<AddressDto> addresses;
}
