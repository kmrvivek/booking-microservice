package com.ms.core.user.event;

import lombok.Data;

@Data
public class NewAddressCreatedEvent {

	private String userId;
	private String houseNo;
	private String apartmentName;
	private String fullAddress;
	private String pincode;
	private String city;
	private String state;
	private String country;
	private boolean active;
	private boolean defaultAddress;
}
