package com.online.discount.strategy;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.online.discount.configuration.DiscountConfiguration;
import com.online.discount.model.DiscountRule;

@Component
public class DayDiscountStrategy implements DiscountCalculationStrategy {

  @Autowired private DiscountConfiguration discountConfiguration;

  @Override
  public double calculateDiscount(
      String category, double totalPrice, int ticketCount, LocalDateTime bookingDate) {

    double discountPercentage =
        getDiscountPercentageForDayOfWeek(category, bookingDate.getDayOfWeek().getValue());

    return totalPrice * (1 - discountPercentage / 100);
  }

  private double getDiscountPercentageForDayOfWeek(String category, int dayOfWeek) {
    List<DiscountRule> rules = discountConfiguration.getRules();
    for (DiscountRule rule : rules) {
      if ("BookingDay".equals(rule.getCategory()) && rule.getDayOfWeek() == dayOfWeek) {
        return rule.getDiscountPercentage();
      }
    }
    return 0;
  }
}
