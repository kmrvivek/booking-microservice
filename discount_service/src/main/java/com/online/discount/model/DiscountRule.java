package com.online.discount.model;

import lombok.Data;

@Data
public class DiscountRule {

  private String category;

  private double discountPercentage;

  private int dayOfWeek;

  private int bookingCountThreshold;

  private double bookingThresholdDiscountPercentage;

  private String dayTime;
}
