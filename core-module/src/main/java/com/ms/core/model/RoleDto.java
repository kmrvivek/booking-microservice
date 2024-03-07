package com.ms.core.model;

import java.util.Date;

import lombok.Data;

@Data
public class RoleDto {

	private long id;
	private String roleName;
	private boolean active;
	private Date insertDate;
	private Date updateDate;
	
}
