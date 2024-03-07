package com.online.discount.strategy;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyFactory {
  private final Map<String, DiscountCalculationStrategy> strategyMap = new HashMap<>();

  public DiscountStrategyFactory(
      DayDiscountStrategy dayDiscountStrategy,
      TicketCountDiscountStrategy ticketCountDiscountStrategy,
      BookingTimeDiscountStrategy bookingTimeDiscountStrategy) {
    strategyMap.put("Wednesday", dayDiscountStrategy);
    strategyMap.put("TicketCount", ticketCountDiscountStrategy);
    strategyMap.put("BookingTime", bookingTimeDiscountStrategy);
  }

  public DiscountCalculationStrategy getStrategy(String strategyName) {
    return strategyMap.get(strategyName);
  }
}
