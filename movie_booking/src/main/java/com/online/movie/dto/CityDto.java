package com.online.movie.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.online.movie.entity.City;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class CityDto {

  @NotEmpty(message = "*Please provide the country")
  String country;

  @Valid
  @NotEmpty(message = "*Please provide cities to add to your country")
  List<City> cities;
}
