package com.online.movie.entity;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Movie implements Serializable {

  private static final long serialVersionUID = 411596774016607713L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long movieId;

  @Valid
  @NotEmpty(message = "*Please provide title of the movie")
  private String title;

  private String description;

  private Duration duration;

  private String Language;

  @Temporal(TemporalType.TIMESTAMP)
  private Date releaseDate;

  private String country;

  private String genre;

  private boolean isDeleted;
}
