package com.ms.user.command.service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.management.relation.RoleInfoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ms.core.user.exception.NoSuchUserExistsException;
import com.ms.core.user.exception.UserAlreadyExistsException;
import com.ms.core.user.model.AddressDto;
import com.ms.core.user.model.RoleDto;
import com.ms.core.user.model.UpdateUserDto;
import com.ms.core.user.model.UserDto;
import com.ms.user.entity.AddressEntity;
import com.ms.user.entity.RoleEntity;
import com.ms.user.entity.UserEntity;
import com.ms.user.repository.RoleRepository;
import com.ms.user.repository.UserRepository;

@Service
public class UserCommandService implements IUserCommandService {

  private static final Logger LOG = LoggerFactory.getLogger(UserCommandService.class);

  private static final String PROCESSING_STATUS = "PROCESSING";
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserCommandService(
      final UserRepository userRepository, final RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public String createNewUser(final UserDto userDto) {

    if (LOG.isDebugEnabled()) {
      LOG.debug(
          "Inside UserCommandService service: creating new user with email {}: starts",
          userDto.getEmail());
    }

    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
      throw new UserAlreadyExistsException(
          "User is already registered with provided email " + userDto.getEmail());
    }

    final UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDto, userEntity);
    userEntity.setAccountBalance(userDto.getAccountBalance().doubleValue());
    userEntity.setUserId(UUID.randomUUID().toString());

    final UserEntity ue = userRepository.save(userEntity);
    LOG.info(
        "Inside UserCommandService service: creating new user with email {} and userId {}: completed",
        userDto.getEmail(),
        ue.getUserId());
    return "User has been created successfully with UserId " + ue.getUserId();
  }

  @Override
  public void updateExistingUser(final UpdateUserDto updateUserDto) {

    LOG.info(
        "Inside UserCommandService service: updating user with email {} and userId {}: starts",
        updateUserDto.getEmail(),
        updateUserDto.getUserId());

    final UserEntity userEntity =
        userRepository
            .findByUserIdAndEmailAndActive(
                updateUserDto.getUserId(), updateUserDto.getEmail(), true)
            .orElseThrow(
                () -> new NoSuchUserExistsException("User not found with provided userId"));

    userEntity.setContactNumber(updateUserDto.getContactNumber());
    userEntity.setFirstName(updateUserDto.getFirstName());
    userEntity.setLastName(updateUserDto.getLastName());
    userEntity.setActive(updateUserDto.isActive());
    userEntity.setPassword(updateUserDto.getPassword());
    userRepository.save(userEntity);

    LOG.info(
        "Inside UserCommandService service: updating user with email {} and userId {}: ends",
        updateUserDto.getEmail(),
        updateUserDto.getUserId());
  }

  @Override
  public void assignNewRoleToUser(final RoleDto roleDto) throws RoleInfoNotFoundException {

    LOG.info(
        "Inside UserCommandService service: Assigning the role {} to the userId {} Successfully: starts",
        roleDto.getRoleName(),
        roleDto.getUserId());

    final RoleEntity roleEntity =
        roleRepository
            .findByRoleName(roleDto.getRoleName())
            .orElseThrow(
                () ->
                    new RoleInfoNotFoundException(
                        "The Role provided is not defined/valid. Please verify"));

    final UserEntity userEntity =
        userRepository
            .findByUserIdAndActive(roleDto.getUserId(), true)
            .orElseThrow(
                () ->
                    new IllegalArgumentException("The provided userId is not valid. Please check"));

    Set<RoleEntity> roles = userEntity.getRoles();
    if (!roles.add(roleEntity)) {
      throw new IllegalArgumentException("The provided role is already granted to the user");
    }
    userEntity.setRoles(roles);
    userRepository.save(userEntity);

    LOG.info(
        "Inside UserCommandService service: Assigning the role {} to the userId {} with status {} Successfully: completed",
        roles,
        userEntity.getUserId(),
        PROCESSING_STATUS);
  }

  @Override
  public void assignNewAddressToUser(final AddressDto addressDto) {

    LOG.info(
        "Inside UserCommandService service: Assigning the addresss {} to the userId {} Successfully: starts",
        addressDto,
        addressDto.getUserId());

    final UserEntity userEntity =
        userRepository
            .findByUserIdAndActive(addressDto.getUserId(), true)
            .orElseThrow(
                () ->
                    new IllegalArgumentException("The provided userId is not valid. Please check"));

    final AddressEntity addressEntity = new AddressEntity();
    BeanUtils.copyProperties(addressDto, addressEntity);

    Set<AddressEntity> addresses = userEntity.getAddresses();

    if (addressDto.isDefaultAddress()) {

      addresses =
          userEntity.getAddresses().stream()
              .map(
                  add -> {
                    add.setDefaultAddress(false);
                    return add;
                  })
              .collect(Collectors.toSet());
    }

    addresses.add(addressEntity);
    userEntity.setAddresses(addresses);

    userRepository.save(userEntity);

    LOG.info(
        "Inside UserCommandService service: Assigning the addresss {} to the userId {} Successfully: ends",
        addresses,
        userEntity.getUserId());
  }
}
