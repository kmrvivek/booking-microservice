package com.ms.user.query.controller;

import static com.ms.user.support.UserServiceConstants.CHECK_IF_USER_EXISTS;
import static com.ms.user.support.UserServiceConstants.CHECK_IF_VALID_USER_WITH_ROLE;
import static com.ms.user.support.UserServiceConstants.GET_USER_DETAIL;
import static com.ms.user.support.UserServiceConstants.LIST_ACTIVE_USERS;
import static com.ms.user.support.UserServiceConstants.LIST_ALL_USERS;
import static com.ms.user.support.UserServiceConstants.USER_API;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.ms.core.user.model.UserObj;
import com.ms.user.query.service.IUserQueryService;

@Validated
@RestController
@RequestMapping(USER_API)
public class UserQueryController {

	private static final Logger LOG = LoggerFactory.getLogger(UserQueryController.class);

	@Autowired
	private IUserQueryService userQueryService;

	@GetMapping(LIST_ALL_USERS)
	public ResponseEntity<List<UserObj>> getAllUsers() {

		LOG.info("Inside User MS: retrieving all users request: starts");
		final List<UserObj> userLists = userQueryService.getAllUsers();
		LOG.info("Inside User MS: retrieving all users request: ends");
		return new ResponseEntity<>(ImmutableList.copyOf(userLists), HttpStatus.OK);
	}

	@GetMapping(LIST_ACTIVE_USERS)
	public ResponseEntity<List<UserObj>> getAllActiveUsers() {

		LOG.info("Inside User MS: retrieving all active users request: starts");
		final List<UserObj> userLists = userQueryService.getAllActiveUsersOnly();
		LOG.info("Inside User MS: retrieving all active users request: starts");
		return new ResponseEntity<>(ImmutableList.copyOf(userLists), HttpStatus.OK);
	}

	@GetMapping(GET_USER_DETAIL)
	public ResponseEntity<UserObj> getUserInfo(@PathVariable("userId") String userId) {

		LOG.info("Inside User MS: invoking getUserInfo request for userId {}", userId);
		return new ResponseEntity<>(userQueryService.getUserByUserId(userId), HttpStatus.OK);
	}

	@GetMapping(CHECK_IF_USER_EXISTS)
	public ResponseEntity<Boolean> checkIfValidUser(
			@PathVariable("userId") @NotBlank(message = "* userId is missing") String userId) {

		LOG.info("Inside User MS: invoking checkIfValidUser request for userId {}", userId);
		return new ResponseEntity<>(userQueryService.checkIfValidUser(userId), HttpStatus.OK);
	}

	@GetMapping(CHECK_IF_VALID_USER_WITH_ROLE)
	public ResponseEntity<Boolean> checkIfValidUserWithRole(
			@PathVariable("userId") @NotBlank(message = "* userId is missing") String userId,
			@PathVariable("role") @NotBlank(message = "* role is missing") String role) {

		LOG.info("Inside User MS: invoking checkIfValidUserWithRole request for userId {} and role {}", userId, role);
		return new ResponseEntity<>(userQueryService.checkIfValidUserWithRole(userId, role), HttpStatus.OK);
	}

}
