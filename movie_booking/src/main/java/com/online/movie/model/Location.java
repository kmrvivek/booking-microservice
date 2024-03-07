package com.online.movie.model;

import com.online.movie.entity.City;
import lombok.Data;

@Data
public class Location {

  private City city;

  private String streetAddress;

  private String navigationLink;
}
