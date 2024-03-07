package com.online.movie.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.movie.dto.CityDto;
import com.online.movie.entity.City;
import com.online.movie.repository.CityRepository;
import com.online.movie.service.CityService;

@Service
public class CityServiceImpl implements CityService {

  private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

  @Autowired private CityRepository cityRepository;

  @Override
  public String addNewCity(CityDto cityDto) {
    log.info("Adding new cities of Country {}", cityDto.getCountry());
    List<City> cities = new ArrayList<>();
    for (City city : cityDto.getCities()) {
      city.setCountry(cityDto.getCountry());
      cities.add(city);
    }
    List<City> savedCities = cityRepository.saveAll(cities);
    log.info("Cities saved: {}", savedCities);

    return "Cities saved sucessfully";
  }

  @Override
  public void deleteCity(Long cityId) {
    log.info("Deleting city: {}", cityId);
    cityRepository.deleteById(cityId);
    log.info("Succesfully deleted the city");
  }
}
