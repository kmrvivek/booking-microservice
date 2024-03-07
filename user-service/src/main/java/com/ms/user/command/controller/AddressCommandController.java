package com.ms.user.command.controller;

import static com.ms.user.support.UserServiceConstants.ADD_NEW_ADDRESS;
import static com.ms.user.support.UserServiceConstants.USER_API;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ms.core.user.model.AddressDto;
import com.ms.user.command.service.IUserCommandService;
import com.ms.user.support.FailedValidationHandler;

@RestController
@RequestMapping(USER_API)
public class AddressCommandController {

  @Autowired private IUserCommandService userCommandService;

  @FailedValidationHandler
  @PostMapping(ADD_NEW_ADDRESS)
  public ResponseEntity<HttpStatus> addNewAddressToUser(
      @Valid @RequestBody AddressDto addressDto, BindingResult bindingResult) {

    userCommandService.assignNewAddressToUser(addressDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.NO_CONTENT);
  }
}
