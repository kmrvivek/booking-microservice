package com.online.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.entity.City;
import java.util.List;
import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Long>{
  
  Optional<List<City>> findByName(String name);
  
  List<City> findByState(String state);
  
  Optional<List<City>> findByCountry(String country);

}
