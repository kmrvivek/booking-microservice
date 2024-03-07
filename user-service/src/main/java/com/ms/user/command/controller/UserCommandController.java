package com.ms.user.command.controller;

import static com.ms.user.support.UserServiceConstants.ADD_NEW_USER;
import static com.ms.user.support.UserServiceConstants.UPDATE_USER_INFO;
import static com.ms.user.support.UserServiceConstants.USER_API;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ms.core.user.model.UpdateUserDto;
import com.ms.core.user.model.UserDto;
import com.ms.user.command.service.IUserCommandService;
import com.ms.user.support.FailedValidationHandler;

@RestController
@RequestMapping(USER_API)
public class UserCommandController {

  @Autowired private IUserCommandService userCommandService;

  @FailedValidationHandler
  @PostMapping(ADD_NEW_USER)
  public ResponseEntity<String> addNewUser(
      @Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

    return new ResponseEntity<>(userCommandService.createNewUser(userDto), HttpStatus.CREATED);
  }

  @FailedValidationHandler
  @PutMapping(UPDATE_USER_INFO)
  public ResponseEntity<String> updateUser(
      @Valid @RequestBody UpdateUserDto userDto, BindingResult bindingResult) {

    userCommandService.updateExistingUser(userDto);
    return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
  }
}
