package com.ms.core.common;

public enum UserSupportedRole {

	ADMIN, CUSTOMER, SUPPORT, SELLER;

	public static boolean isSupportedStateAndCityAndPincode(String args) {

		for (UserSupportedRole role : values()) {
			if (role.name().equals(args)) {
				return true;
			}
		}
		return false;
	}

}
