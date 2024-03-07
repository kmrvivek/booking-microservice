package com.online.movie.model;

import lombok.Data;

@Data
public class ScreenSeats {

  String startRow;

  String endRow;

  String type;

  int numberOfSeats;
}
