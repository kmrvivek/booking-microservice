package com.online.movie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.online.movie.dto.CityDto;
import com.online.movie.service.CityService;

@RestController
@RequestMapping("/v1/api/")
public class CityController {

  private static final Logger log = LoggerFactory.getLogger(CityController.class);

  @Autowired private CityService cityService;

  @PostMapping("/city")
  public ResponseEntity<String> createTheatre(@RequestBody CityDto cityDto) {
    log.info("Adding new city {}", cityDto);
    return new ResponseEntity<String>(cityService.addNewCity(cityDto), HttpStatus.CREATED);
  }
}
