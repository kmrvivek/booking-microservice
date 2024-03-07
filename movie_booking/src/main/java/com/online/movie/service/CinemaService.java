package com.online.movie.service;

import java.util.List;
import java.util.Set;
import com.online.movie.dto.ShowDto;
import com.online.movie.dto.TheatreDto;
import com.online.movie.entity.CinemaHall;
import com.online.movie.entity.Price;

public interface CinemaService {

  public CinemaHall addTheatre(TheatreDto theatreDto);

  public void removeTheatre(Long cinemaHallId);

  String removeShows(Long cinemaHallId, Set<Long> showIds);

  String addShows(ShowDto showDto);

  void createMoviePrice(Long cinemaHallId, Long showId, List<Price> typePricing);
}
