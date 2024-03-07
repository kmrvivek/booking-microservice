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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Booking implements Serializable {

  private static final long serialVersionUID = 8547934436932876402L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int bookingId;

  private int numberOfSeats;

  @OneToMany(mappedBy = "seatId", fetch = FetchType.LAZY)
  private List<Seat> seats;

  @Temporal(TemporalType.TIMESTAMP)
  private Date bookingTimeStamp;

  private String userId;

  private String bookingStatus;

  private String paymentId;

  @OneToOne
  @JoinColumn(name = "showId", nullable = false)
  private MovieShow show;
}
