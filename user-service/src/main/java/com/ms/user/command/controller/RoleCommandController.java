package com.ms.user.command.controller;

import static com.ms.user.support.UserServiceConstants.*;

import javax.management.relation.RoleInfoNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.core.user.model.RoleDto;
import com.ms.user.command.service.IUserCommandService;
import com.ms.user.support.FailedValidationHandler;

@RestController
@RequestMapping(USER_API)
public class RoleCommandController {

	@Autowired
	private IUserCommandService userCommandService;

	@FailedValidationHandler
	@PostMapping(ADD_NEW_ROLE)
	public ResponseEntity<HttpStatus> addNewRoleToUSer(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) throws RoleInfoNotFoundException {

		userCommandService.assignNewRoleToUser(roleDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT, HttpStatus.CREATED);
	}

}
