package com.ms.core.user.event;

import lombok.Data;

@Data
public class NewRoleCreatedEvent {

	private String userUUId;
	private String roleName;
}
