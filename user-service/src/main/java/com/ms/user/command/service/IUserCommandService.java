package com.ms.user.command.service;

import javax.management.relation.RoleInfoNotFoundException;
import com.ms.core.user.model.AddressDto;
import com.ms.core.user.model.RoleDto;
import com.ms.core.user.model.UpdateUserDto;
import com.ms.core.user.model.UserDto;

public interface IUserCommandService {

  String createNewUser(UserDto userDto);

  void updateExistingUser(UpdateUserDto updateUserDto);

  void assignNewRoleToUser(RoleDto userDto) throws RoleInfoNotFoundException;

  void assignNewAddressToUser(AddressDto addressDto);
}
