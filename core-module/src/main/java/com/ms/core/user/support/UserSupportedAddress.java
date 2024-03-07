package com.ms.core.user.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public enum UserSupportedAddress {

	DL("DELHI", Arrays.asList(new HashMap<String, List<String>>() {
		{
			put("DELHI", Arrays.asList("60001"));
		}
	})), KA("KARNATAKA", Arrays.asList(new HashMap<String, List<String>>() {
		{
			put("BANGALORE", Arrays.asList("560093"));
		} 
	})), TN("TAMILNADU", Arrays.asList(new HashMap<String, List<String>>() {
		{
			put("CHENNAI", Arrays.asList("876543"));
		}
	})), MH("MAHARASHTRA", Arrays.asList(new HashMap<String, List<String>>() {
		{
			put("MUMBAI", Arrays.asList("488776"));
		}
	}));

	private static final Map<String, List<String>> BY_CITY_SUPPORTED_PINCODE = new HashMap<>();
	private static final Map<String, List<String>> BY_STATE_SUPPORTED_CITY = new HashMap<>();

	static {
		for (UserSupportedAddress e : values()) {
			List<String> cities = new ArrayList<>();
			for (Map<String, List<String>> citymap : e.cities) {
				for (Entry<String, List<String>> entry : citymap.entrySet()) {
					cities.add(entry.getKey());
					BY_CITY_SUPPORTED_PINCODE.put(entry.getKey(), entry.getValue());
				}
			}
			BY_STATE_SUPPORTED_CITY.put(e.state, cities);
		}
	}

	private final String state;
	private final List<Map<String, List<String>>> cities;

	private UserSupportedAddress(String state, List<Map<String, List<String>>> cities) {
		this.state = state;
		this.cities = cities;
	}

	public static boolean isSupportedStateAndCityAndPincode(String state, String city, String pincode) {

		return BY_STATE_SUPPORTED_CITY.get(state) != null && BY_STATE_SUPPORTED_CITY.get(state).contains(city)
				&& BY_CITY_SUPPORTED_PINCODE.get(city) != null && BY_CITY_SUPPORTED_PINCODE.get(city).contains(pincode);
	}
}
