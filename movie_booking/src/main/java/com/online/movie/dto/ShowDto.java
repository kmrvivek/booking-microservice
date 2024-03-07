package com.online.movie.dto;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.online.movie.entity.Price;
import lombok.Data;

@Data
public class ShowDto {

  private Long theatreId;

  private List<Price> prices;

  private Long screenId;

  private Long movieId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date showStartDate;

  @DateTimeFormat(pattern = "HH:mm")
  private Date startTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date showEndDate;
}
