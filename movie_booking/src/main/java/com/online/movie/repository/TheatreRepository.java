package com.online.movie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.entity.CinemaHall;
import com.online.movie.entity.City;

public interface TheatreRepository extends JpaRepository<CinemaHall, Long> {

  List<CinemaHall> findByCity(City city);

  List<CinemaHall> findByName(String name);
}
