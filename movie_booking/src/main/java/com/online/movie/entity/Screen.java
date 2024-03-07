package com.online.movie.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Screen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long screenId;

  private String screenName;

  @OneToMany(mappedBy = "seatId", cascade = CascadeType.ALL)
  private List<Seat> seats;

  @OneToMany(mappedBy = "showId", cascade = CascadeType.ALL)
  private List<MovieShow> shows;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cinemaHallId")
  private CinemaHall cinemaHall;
}
