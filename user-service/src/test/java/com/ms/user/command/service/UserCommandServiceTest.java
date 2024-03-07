package com.ms.user.command.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.management.relation.RoleInfoNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserCommandServiceTest {

  @InjectMocks private UserCommandService userCommandService;

  @Mock private UserRepository mockUserRepository;
  @Mock private RoleRepository mockRoleRepository;
  @Mock private UUID uuidMock;

  @Test
  public void createNewUserWithExistingEmailTest() {

    final UserEntity ue = new UserEntity();
    ue.setEmail("vk07658@gmail.com");
    Optional<UserEntity> userEntities = Optional.of(ue);

    doReturn(userEntities).when(mockUserRepository).findByEmail(ue.getEmail());

    UserDto userDto = getUserDtoObj();

    assertThatThrownBy(() -> userCommandService.createNewUser(userDto))
        .isInstanceOf(UserAlreadyExistsException.class);

    assertThatThrownBy(() -> userCommandService.createNewUser(userDto))
        .hasMessage("User is already registered with provided email " + userDto.getEmail());
  }

  @Test
  public void success_createNewUserTest() {

    final String userId = UUID.randomUUID().toString();
    final UserEntity userEntity = getUserEntityObj(userId);

    doReturn(Optional.empty()).when(mockUserRepository).findByEmail(userEntity.getEmail());

    when(mockUserRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

    Mockito.mockStatic(UUID.class);
    Mockito.when(UUID.randomUUID()).thenReturn(uuidMock);
    Mockito.when(uuidMock.toString()).thenReturn(userId);

    Assertions.assertThat(userCommandService.createNewUser(getUserDtoObj()))
        .isEqualTo("User has been created successfully with UserId " + userId);
  }

  @Test
  public void updateExistingUser_inactiveUser() {

    final String userId = UUID.randomUUID().toString();

    final UpdateUserDto updateUserDto = new UpdateUserDto();
    updateUserDto.setFirstName("Vivek");
    updateUserDto.setLastName("Kumar");
    updateUserDto.setEmail("rkg130790@gmail.com");
    updateUserDto.setContactNumber("9560032156");
    updateUserDto.setPassword("Hello1232");
    updateUserDto.setUserId(userId);

    doReturn(Optional.empty())
        .when(mockUserRepository)
        .findByUserIdAndEmailAndActive(userId, updateUserDto.getEmail(), true);

    assertThatThrownBy(() -> userCommandService.updateExistingUser(updateUserDto))
        .isInstanceOf(NoSuchUserExistsException.class);

    assertThatThrownBy(() -> userCommandService.updateExistingUser(updateUserDto))
        .hasMessage("User not found with provided userId");
  }

  @Test
  public void updateExistingUser_activeUser() {

    final String userId = UUID.randomUUID().toString();

    final UpdateUserDto updateUserDto = new UpdateUserDto();
    updateUserDto.setFirstName("Vivek");
    updateUserDto.setLastName("Kumar");
    updateUserDto.setEmail("vk07658@gmail.com");
    updateUserDto.setContactNumber("8789000069");
    updateUserDto.setPassword("Hello1232");
    updateUserDto.setUserId(userId);

    doReturn(Optional.of(getUserEntityObj(userId)))
        .when(mockUserRepository)
        .findByUserIdAndEmailAndActive(userId, updateUserDto.getEmail(), true);

    userCommandService.updateExistingUser(updateUserDto);

    Mockito.verify(this.mockUserRepository, times(1)).save(getUserEntityObj(userId));
  }

  @Test
  public void assignNewRoleToUser_RoleInfoNotFoundException() {

    RoleDto roleDto = new RoleDto();
    roleDto.setRoleName("APP_CONSUMER");

    doReturn(Optional.empty()).when(mockRoleRepository).findByRoleName(roleDto.getRoleName());

    assertThatThrownBy(() -> userCommandService.assignNewRoleToUser(roleDto))
        .isInstanceOf(RoleInfoNotFoundException.class);

    assertThatThrownBy(() -> userCommandService.assignNewRoleToUser(roleDto))
        .hasMessage("The Role provided is not defined/valid. Please verify");
  }

  @Test
  public void assignNewRoleToUser_InvalidUserIdException() throws RoleInfoNotFoundException {

    RoleDto roleDto = new RoleDto();
    roleDto.setRoleName("CUSTOMER");
    roleDto.setUserId("user-id-test");

    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(1);
    roleEntity.setRoleName("ADMIN");
    roleEntity.setRoleName("admin");

    doReturn(Optional.of(roleEntity))
        .when(mockRoleRepository)
        .findByRoleName(roleDto.getRoleName());

    doReturn(Optional.of(getUserEntityObj(roleDto.getUserId())))
        .when(mockUserRepository)
        .findByUserIdAndActive(roleDto.getUserId(), true);

    assertThatThrownBy(() -> userCommandService.assignNewRoleToUser(roleDto))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> userCommandService.assignNewRoleToUser(roleDto))
        .hasMessage("The provided role is already granted to the user");
  }

  @Test
  public void assignNewAddressToUser_InvalidUserIdException() throws RoleInfoNotFoundException {

    AddressDto addressDto = new AddressDto();
    addressDto.setApartmentName("AP NO A2");
    addressDto.setCity("DELHI");
    addressDto.setHouseNo("A2");
    addressDto.setPincode("560093");
    addressDto.setUserId(UUID.randomUUID().toString());

    doReturn(Optional.empty())
        .when(mockUserRepository)
        .findByUserIdAndActive(addressDto.getUserId(), true);

    assertThatThrownBy(() -> userCommandService.assignNewAddressToUser(addressDto))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> userCommandService.assignNewAddressToUser(addressDto))
        .hasMessage("The provided userId is not valid. Please check");
  }

  @Test
  public void assignNewAddressToUser_validUserIdException() throws RoleInfoNotFoundException {
    ArgumentCaptor<UserEntity> captorParam1 = ArgumentCaptor.forClass(UserEntity.class);
    AddressDto addressDto = new AddressDto();
    addressDto.setApartmentName("AP NO A2");
    addressDto.setCity("DELHI");
    addressDto.setHouseNo("A2");
    addressDto.setPincode("560093");
    addressDto.setUserId(UUID.randomUUID().toString());

    doReturn(Optional.of(getUserEntityObj(addressDto.getUserId())))
        .when(mockUserRepository)
        .findByUserIdAndActive(addressDto.getUserId(), true);

    userCommandService.assignNewAddressToUser(addressDto);

    Mockito.verify(this.mockUserRepository, times(1)).save(captorParam1.capture());
    Assertions.assertThat(captorParam1.getValue().getAccountBalance()).isEqualTo(15000.24);
  }

  private UserEntity getUserEntityObj(String userId) {

    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(1);
    roleEntity.setRoleName("admin");

    Set<RoleEntity> roles = new HashSet<>();
    roles.add(roleEntity);

    AddressEntity addressDto = new AddressEntity();
    addressDto.setApartmentName("AP NO A2");
    addressDto.setCity("DELHI");
    addressDto.setHouseNo("A2");
    addressDto.setPincode("560093");

    Set<AddressEntity> addresses = new HashSet<>();
    addresses.add(addressDto);

    final UserEntity ue = new UserEntity();
    ue.setFirstName("Vivek");
    ue.setLastName("Kumar");
    ue.setEmail("vk07658@gmail.com");
    ue.setAccountBalance(15000.24);
    ue.setContactNumber("8789000069");
    ue.setPassword("Hello1232");
    ue.setUserId(userId);
    ue.setRoles(roles);
    ue.setAddresses(addresses);
    return ue;
  }

  private UserDto getUserDtoObj() {
    final UserDto userDto = new UserDto();
    userDto.setFirstName("Vivek");
    userDto.setLastName("Kumar");
    userDto.setEmail("vk07658@gmail.com");
    userDto.setAccountBalance(BigDecimal.valueOf(15000.24));
    userDto.setContactNumber("8789000069");
    userDto.setPassword("Hello1232");
    return userDto;
  }
}
