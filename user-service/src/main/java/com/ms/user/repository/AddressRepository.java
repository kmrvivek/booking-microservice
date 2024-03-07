package com.ms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
