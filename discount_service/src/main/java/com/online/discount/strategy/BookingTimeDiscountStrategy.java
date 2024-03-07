package com.online.discount.strategy;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.online.discount.configuration.DiscountConfiguration;
import com.online.discount.model.DiscountRule;

public class BookingTimeDiscountStrategy implements DiscountCalculationStrategy {

  @Autowired private DiscountConfiguration discountConfiguration;

  @Override
  public double calculateDiscount(
      String category, double totalPrice, int ticketCount, LocalDateTime bookingDate) {
    double discountPercentage = 0;
    ZonedDateTime zdt = bookingDate.atZone(ZoneId.systemDefault());
    LocalTime lt = zdt.toLocalTime();

    if (lt.isAfter(LocalTime.of(12, 0, 0)) && lt.isBefore(LocalTime.of(18, 0, 0))) {
      discountPercentage =
          Math.max(discountPercentage, getDiscountPercentageForBookingTime(category, "Afternoon"));
    }

    return totalPrice * (1 - discountPercentage / 100);
  }

  private double getDiscountPercentageForBookingTime(String category, String bookingSlot) {
    List<DiscountRule> rules = discountConfiguration.getRules();
    for (DiscountRule rule : rules) {
      if ("BookingTime".equals(rule.getCategory()) && rule.getDayTime() == bookingSlot) {
        return rule.getDiscountPercentage();
      }
    }
    return 0;
  }
}
