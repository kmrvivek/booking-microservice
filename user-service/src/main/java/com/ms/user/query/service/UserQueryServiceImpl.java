package com.ms.user.query.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ms.core.user.exception.NoSuchUserExistsException;
import com.ms.core.user.model.AddressDto;
import com.ms.core.user.model.RoleDto;
import com.ms.core.user.model.UserObj;
import com.ms.user.entity.AddressEntity;
import com.ms.user.entity.RoleEntity;
import com.ms.user.entity.UserEntity;
import com.ms.user.repository.UserRepository;

@Service
public class UserQueryServiceImpl implements IUserQueryService {

	private final UserRepository userRepository;

	public UserQueryServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserObj> getAllUsers() {

		return userRepository.findAll().stream().map(this::convertUserEntityIntoUserDto).collect(Collectors.toList());

	}

	@Override
	public List<UserObj> getAllActiveUsersOnly() {

		return userRepository.findAll().stream().filter(UserEntity::isActive).map(this::convertUserEntityIntoUserDto)
				.collect(Collectors.toList());

	}

	@Override
	public UserObj getUserByUserId(String userId) {

		final Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
		if (!userEntity.isPresent()) {
			throw new NoSuchUserExistsException("UserEntity not found with provided userId");
		}
		return convertUserEntityIntoUserDto(userEntity.get());

	}

	@Override
	public boolean checkIfValidUser(String userId) {

		final Optional<UserEntity> userEntity = getUserData(userId);
		return userEntity.isPresent() && userEntity.get().isActive();
	}

	@Override
	public boolean checkIfValidUserWithRole(String userId, String roleName) {

		final Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
		if (userEntity.isPresent()) {
			return userEntity.get().getRoles().stream()
					.anyMatch(role -> roleName.toUpperCase().equals(role.getRoleName()));
		}
		return false;
	}

	private Optional<UserEntity> getUserData(String userId) {
		return userRepository.findByUserId(userId);
	}

	private UserObj convertUserEntityIntoUserDto(UserEntity userEntity) {
		final Set<AddressDto> addList = new HashSet<>();
		for (AddressEntity address : userEntity.getAddresses()) {
			final AddressDto addressDto = new AddressDto();
			BeanUtils.copyProperties(address, addressDto);
			addList.add(addressDto);
		}

		final Set<RoleDto> roleList = new HashSet<>();
		for (RoleEntity role : userEntity.getRoles()) {
			final RoleDto roleDto = new RoleDto();
			BeanUtils.copyProperties(role, roleDto);
			roleList.add(roleDto);
		}

		final UserObj userObj = new UserObj();
		BeanUtils.copyProperties(userEntity, userObj);
		userObj.setRoles(roleList);
		userObj.setAddresses(addList);
		userObj.setAccountBalance(BigDecimal.valueOf(userEntity.getAccountBalance()));

		return userObj;
	}

}
