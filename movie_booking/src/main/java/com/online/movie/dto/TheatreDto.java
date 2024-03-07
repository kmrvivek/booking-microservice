package com.online.movie.dto;

import java.util.List;
import java.util.Map;
import com.online.movie.model.ScreenSeats;
import lombok.Data;

@Data
public class TheatreDto {

  private String name;

  private int totalSeats;

  private Map<String, List<ScreenSeats>> screenSeats;

  private Long cityId;

  private String address;
}
