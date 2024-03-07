package com.ms.core.user.event;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UserPaymentProcessedEvent {

	private String userId;
	private String orderId;
	private String paymentId;
	private BigDecimal requestedAmount;
	private String email;
}
