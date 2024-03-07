package com.ms.core.model;

import lombok.Data;

@Data
public class AddressDto {

	private String houseNo;
	private String apartmentName;
	private String fullAdress;
	private String pincode;
	private String city;
	private String state;
	private String country;
	private boolean active;
	private boolean defaultAddress;
}
