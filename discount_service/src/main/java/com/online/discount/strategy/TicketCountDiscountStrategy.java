package com.online.discount.strategy;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.online.discount.configuration.DiscountConfiguration;
import com.online.discount.model.DiscountRule;

public class TicketCountDiscountStrategy implements DiscountCalculationStrategy {

  @Autowired private DiscountConfiguration discountConfiguration;

  @Override
  public double calculateDiscount(
      String category, double totalPrice, int ticketCount, LocalDateTime bookingDate) {
    double discountPercentage = 0;

    // Check if item count threshold is met and apply the discount
    if (ticketCount >= 3) {
      discountPercentage =
          Math.max(discountPercentage, getDiscountPercentageForItemCount(category, ticketCount));
    }

    return totalPrice - (totalPrice / 3 * (1 - discountPercentage / 100));
  }

  private double getDiscountPercentageForItemCount(String category, int itemCount) {
    List<DiscountRule> rules = discountConfiguration.getRules();
    for (DiscountRule rule : rules) {
      if ("Any".equals(rule.getCategory()) && rule.getBookingCountThreshold() >= itemCount) {
        return rule.getBookingThresholdDiscountPercentage();
      }
    }
    return 0;
  }
}
