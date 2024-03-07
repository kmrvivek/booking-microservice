package com.online.movie.dto;

import java.util.Date;
import java.util.Map;
import com.online.movie.model.Location;
import lombok.Data;

@Data
public class TheatreMoviePriceDto {

  private String theatreName;

  private Date showDate;

  private Map<Date, Map<String, Double>> priceMap;

  private Location location;
}
