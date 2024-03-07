package com.online.discount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.online.discount.model.TicketDetailDto;
import com.online.discount.strategy.DiscountCalculationStrategy;
import com.online.discount.strategy.DiscountStrategyFactory;

@RestController
@RequestMapping("/v1/api")
public class DiscountController {
  @Autowired private DiscountStrategyFactory discountStrategyFactory;

  @GetMapping("/discount")
  public ResponseEntity<Double> calculateDiscount(@RequestBody TicketDetailDto ticketDetailDto) {
    DiscountCalculationStrategy discountStrategy =
        discountStrategyFactory.getStrategy(ticketDetailDto.getStrategy());
    double discount =
        discountStrategy.calculateDiscount(
            ticketDetailDto.getCategory(),
            ticketDetailDto.getTotalPrice(),
            ticketDetailDto.getItemCount(),
            ticketDetailDto.getBookingDate());
    return new ResponseEntity<Double>(discount, HttpStatus.ACCEPTED);
  }
}
