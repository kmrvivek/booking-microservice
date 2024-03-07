package com.ms.core.user.event;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {

	private final String userId;
	private final String email;
	private final String password;
	private final String firstName;
	private final String contactNumber;
	private final String lastName;
	private final boolean active;
	private final BigDecimal accountBalance;
}
