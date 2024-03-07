package com.online.movie.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seatId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "screenId")
  private Screen screen;

  private String seatNumber;

  private String seatType;

  private boolean booked;
}
