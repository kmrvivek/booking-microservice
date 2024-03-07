package com.online.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Price {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long priceId;

  private String seatType;

  private double cost;
}
