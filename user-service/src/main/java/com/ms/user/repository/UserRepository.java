package com.ms.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserIdAndEmailAndContactNumber(String userId, String email, String contactNo);

	Optional<UserEntity> findByEmailOrContactNumber(String email, String contactNo);
	
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUserIdAndEmailAndActive(String userId, String email, boolean active);

	Optional<UserEntity> findByUserId(String userId);

	Optional<UserEntity> findByUserIdAndActive(String userId, boolean active);
}
