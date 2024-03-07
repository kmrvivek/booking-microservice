package com.online.movie.service;

import com.online.movie.entity.Movie;
import java.util.List;

public interface MovieService {

  public String addMovie(List<Movie> movie);
  
  public void removeMovie(Long movieId);

}
