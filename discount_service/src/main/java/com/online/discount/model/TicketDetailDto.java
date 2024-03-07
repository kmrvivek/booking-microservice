package com.online.discount.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class TicketDetailDto {

  private String category;

  private double totalPrice;

  private int itemCount;

  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime bookingDate;

  private String strategy;
}
