package com.ms.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	public Optional<RoleEntity> findByRoleName(String roleName);
}
