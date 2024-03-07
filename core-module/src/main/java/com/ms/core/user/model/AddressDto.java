package com.ms.core.user.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ms.core.user.support.ValidateAddressDetails;

@ValidateAddressDetails(message = "*please verify user city/state/pincode")
public class AddressDto {

	@NotEmpty(message = "*Please provide an userId")
	private String userId;
	@NotEmpty(message = "*Please provide an houseNo")
	private String houseNo;
	@NotEmpty(message = "*Please provide an apartmentName")
	private String apartmentName;
	@NotEmpty(message = "*Please provide an fullAdress")
	private String fullAddress;
	@NotEmpty(message = "*Please provide an pincode")
	private String pincode;
	@NotEmpty(message = "*Please provide an city")
	private String city;
	@NotEmpty(message = "*Please provide an state")
	private String state;
	@NotEmpty(message = "*Please provide an country")
	private String country;
	private boolean active = true;
	private boolean defaultAddress = false;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	
}
