package com.ms.user.support;

public final class UserServiceConstants {
	
	public static final String USER_API = "/api/user";
	public static final String ADD_NEW_USER = "/addNewUser";
	public static final String ADD_NEW_ROLE = "/addNewRoleToUser";
	public static final String ADD_NEW_ADDRESS = "/addNewAddress";
	public static final String UPDATE_USER_INFO = "/updateUser";
	public static final String PROCESS_ORDER_PAYMENT = "/processOrderPayment";
	public static final String BLOCK_USER_PAYMENT = "/blockPayment/{orderId}/{customerId}/{totalAmount}";
	public static final String UPDATE_USER_PAYMENT = "/updateUserPayment/{orderId}/{customerId}/{status}/{reason}";
	public static final String ADD_FUNDS = "/addFunds/{userId}/{amount}";
	
	public static final String LIST_ALL_USERS = "/list/all";
	public static final String LIST_ACTIVE_USERS = "/list/active/all";
	public static final String GET_USER_DETAIL = "/info/{userId}";
	public static final String CHECK_IF_USER_EXISTS = "/exists/user/{userId}";
	public static final String CHECK_IF_VALID_USER_WITH_ROLE = "/exists/{userId}/role/{role}";
	
	private UserServiceConstants() throws InstantiationException {
		throw new InstantiationException("Instantiation is not Allowed!");
	}

}
