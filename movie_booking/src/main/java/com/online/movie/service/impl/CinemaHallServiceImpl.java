package com.online.movie.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.movie.dto.ShowDto;
import com.online.movie.dto.TheatreDto;
import com.online.movie.entity.CinemaHall;
import com.online.movie.entity.City;
import com.online.movie.entity.Movie;
import com.online.movie.entity.MovieShow;
import com.online.movie.entity.Price;
import com.online.movie.entity.Screen;
import com.online.movie.repository.CityRepository;
import com.online.movie.repository.MovieRepository;
import com.online.movie.repository.ScreenRepository;
import com.online.movie.repository.TheatreRepository;
import com.online.movie.service.CinemaService;
import com.online.movie.util.ScreenSeatCreator;

@Service
public class CinemaHallServiceImpl implements CinemaService {

  private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

  @Autowired private TheatreRepository cinemaRepository;

  @Autowired private ScreenSeatCreator screenSeatCreator;

  @Autowired private MovieRepository movieRepository;

  @Autowired private ScreenRepository screenRepository;

  @Autowired private CityRepository cityRepository;

  @Override
  public CinemaHall addTheatre(TheatreDto theatreDto) {
    log.info("Adding Theatre {}", theatreDto.getName());
    CinemaHall cinemaHall = new CinemaHall();
    List<Screen> screens = screenSeatCreator.createScreen(theatreDto.getScreenSeats());
    City city = cityRepository.findById(theatreDto.getCityId()).get();
    cinemaHall.setCity(city);
    cinemaHall.setName(theatreDto.getName());
    cinemaHall.setStreetAddress(theatreDto.getAddress());
    cinemaHall.setScreens(screens);
    cinemaHall = cinemaRepository.save(cinemaHall);
    log.info("Cinema Created successfully!!\n{}", cinemaHall);
    return cinemaHall;
  }

  @Override
  public String addShows(ShowDto showDto) {
    Optional<CinemaHall> cinemaHall = cinemaRepository.findById(showDto.getTheatreId());
    List<MovieShow> shows = new ArrayList<>();
    if (cinemaHall.isPresent()) {
      CinemaHall theatre = cinemaHall.get();
      Screen screen =
          theatre.getScreens().stream()
              .filter(sc -> sc.getScreenId().equals(showDto.getScreenId()))
              .findFirst()
              .get();
      Movie movie = movieRepository.findById(showDto.getMovieId()).get();
      java.util.Date startDate = showDto.getShowStartDate();
      while (startDate.before(showDto.getShowEndDate())) {
        MovieShow show = new MovieShow();
        show.setMovie(movie);
        show.setPrices(showDto.getPrices());
        show.setScreen(screen);
        show.setMovieDate(showDto.getShowStartDate());
        show.setMovieTime(showDto.getStartTime());
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, 1);
        startDate = c.getTime();
        shows.add(show);
      }
      if (screen.getShows() != null) {
        screen.getShows().addAll(shows);
      } else {
        screen.setShows(new ArrayList<>(shows));
      }

      screenRepository.save(screen);
      log.info("Added new shows to Theatre {}\n{}", theatre.getName(), screen);
      return "Shows added successfully to " + theatre.getName();
    }
    return "Theatre doesn't exist. Please verify again.";
  }

  @Override
  public String removeShows(Long cinemaHallId, Set<Long> showIds) {
    CinemaHall cinemaHall = cinemaRepository.findById(cinemaHallId).get();
    List<MovieShow> showsToRemove = new ArrayList<>();
    cinemaHall
        .getScreens()
        .forEach(
            screen ->
                screen
                    .getShows()
                    .forEach(
                        show -> {
                          if (showIds.contains(show.getShowId())) {
                            showsToRemove.add(show);
                          }
                        }));

    cinemaHall.getScreens().stream().forEach(screen -> screen.getShows().removeAll(showsToRemove));
    cinemaRepository.save(cinemaHall);
    return "Shows removed from Theatre " + cinemaHall.getName();
  }

  @Override
  public void removeTheatre(Long cinemaHallId) {
    cinemaRepository.deleteById(cinemaHallId);
    log.info("Theatre deleted {}", cinemaHallId);
  }

  @Override
  public void createMoviePrice(Long cinemaHallId, Long showId, List<Price> typePricing) {
    CinemaHall cinemaHall = cinemaRepository.findById(cinemaHallId).get();
    for (Screen screen : cinemaHall.getScreens()) {
      screen
          .getShows()
          .forEach(
              show -> {
                if (show.getShowId().equals(showId)) {
                  show.setPrices(typePricing);
                }
              });
    }
    cinemaRepository.save(cinemaHall);
  }
}
