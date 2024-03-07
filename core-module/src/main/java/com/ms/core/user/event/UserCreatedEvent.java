package com.ms.core.user.event;

import java.math.BigDecimal;
import java.util.Set;

import com.ms.core.user.model.AddressDto;
import com.ms.core.user.model.RoleDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {

	private final String userId;
	private final String email;
	private final String password;
	private final String firstName;
	private final String contactNumber;
	private final BigDecimal accountBalance;
	private final String lastName;
	private final boolean active;
	private final Set<RoleDto> roles;
	private final Set<AddressDto> addresses;
}
