package com.online.movie.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class MovieShow implements Serializable {

  private static final long serialVersionUID = 7584521517859922370L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long showId;

  @Temporal(TemporalType.DATE)
  private Date movieDate;

  @Temporal(TemporalType.TIME)
  private Date movieTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "screenId")
  private Screen screen;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movieId")
  private Movie movie;

  @OneToMany(mappedBy = "priceId", fetch = FetchType.LAZY)
  private List<Price> prices;
}
