package com.online.movie.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.movie.entity.Movie;
import com.online.movie.repository.MovieRepository;
import com.online.movie.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

  private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

  @Autowired private MovieRepository movieRepository;

  @Override
  public String addMovie(List<Movie> movies) {
    movieRepository.saveAll(movies);
    log.info("Movie Saved Successfully {}", movies);
    return "Movies successfully saved";
  }

  @Override
  public void removeMovie(Long movieId) {
    log.info("Deleting movie {}", movieId);
    Optional<Movie> movie = movieRepository.findById(movieId);
    if (movie.isPresent()) {
      Movie currentMovie = movie.get();
      currentMovie.setDeleted(true);
      movieRepository.save(currentMovie);
      log.info("Movie soft deleted {}", currentMovie);
    }
  }
}
