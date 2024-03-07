package com.online.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.online.movie.dto.TheatreDto;
import com.online.movie.entity.CinemaHall;
import com.online.movie.service.CinemaService;

@RestController
@RequestMapping("/v1/api/")
public class TheatreController {

  @Autowired private CinemaService cinemaService;

  @PostMapping("/theatre")
  public ResponseEntity<CinemaHall> createTheatre(@RequestBody TheatreDto theatreDto) {
    CinemaHall cinemaHall = cinemaService.addTheatre(theatreDto);
    return new ResponseEntity<CinemaHall>(cinemaHall, HttpStatus.CREATED);
  }
}
