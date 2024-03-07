package com.ms.core.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class UserDto {

	private long id;
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
	private Date insertDate;
	private Date updateDate;
}
