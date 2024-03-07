package com.ms.core.user.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ms.core.user.support.ValidateRoleType;

public class RoleDto {

	private long id;
	@ValidateRoleType(message = "*Please provide valid role Name")
	private String roleName;
	@NotBlank(message = "*please provide userId")
	private String userId;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonProperty(access = Access.READ_ONLY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
