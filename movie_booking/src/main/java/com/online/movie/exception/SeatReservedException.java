package com.online.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Seat Already Reserved")
public class SeatReservedException extends RuntimeException {

  private static final long serialVersionUID = 1840036986680763951L;

  public SeatReservedException() {}

  public SeatReservedException(String msg) {
    super(msg);
  }
}
