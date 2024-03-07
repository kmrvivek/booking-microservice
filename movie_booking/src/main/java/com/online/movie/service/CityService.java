package com.online.movie.service;

import com.online.movie.dto.CityDto;

public interface CityService {
  
  public String addNewCity(CityDto cityDto);
  
  public void deleteCity(Long cityId);

}
