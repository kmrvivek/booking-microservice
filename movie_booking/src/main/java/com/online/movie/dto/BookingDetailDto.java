package com.online.movie.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;

@Data
public class BookingDetailDto {

  private String theatreName;

  private List<String> seatNumbers;

  private double bookingPrice;

  private LocalDate showDate;

  private LocalTime showTime;

  private String address;
}
