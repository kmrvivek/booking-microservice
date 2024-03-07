package com.online.discount.strategy;

import java.time.LocalDateTime;

public interface DiscountCalculationStrategy {

  double calculateDiscount(
      String category, double totalPrice, int ticketCount, LocalDateTime bookingDate);
}
